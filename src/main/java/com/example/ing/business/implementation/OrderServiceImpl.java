package com.example.ing.business.implementation;

import com.example.ing.business.model.OrderModel;
import com.example.ing.business.service.CustomerService;
import com.example.ing.business.service.OrderService;
import com.example.ing.business.service.StockService;
import com.example.ing.persistence.model.OrderEntity;
import com.example.ing.persistence.model.StockEntity;
import com.example.ing.persistence.repository.OrderRepository;
import com.example.ing.utils.enums.OrderSide;
import com.example.ing.utils.enums.OrderStatusEnum;
import com.example.ing.utils.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StockService stockService;

    @Transactional
    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        try {
            if (fieldCheck(orderModel)) {
                if (orderModel.getSide().equals(OrderSide.BUY.toString())){
                    customerService.withdrawMoney(String.valueOf(orderModel.getCustomer().getId()), orderModel.getSize(), false);
                    orderModel.setStatus(OrderStatusEnum.MATCHED.toString());
                }
                if (orderModel.getSide().equals(OrderSide.SELL.toString())){
                    customerService.depositMoney(String.valueOf(orderModel.getCustomer().getId()), orderModel.getSize());
                    orderModel.setStatus(OrderStatusEnum.MATCHED.toString());
                }
                return orderMapper.mapEntityToModel(orderRepository.save(orderMapper.mapModelToEntity(orderModel)));
            }
            else {
                throw new RuntimeException("Customer, Stock or Side fields can not be validated. Please check the related fields.");
            }

        }catch (Exception e){
            throw new RuntimeException("Error while creating order", e);
        }
    }

    @Override
    public OrderModel updateOrder(OrderModel orderModel) {
        OrderEntity orderEntity = orderMapper.mapModelToEntity(orderModel);
        return orderMapper.mapEntityToModel(orderRepository.save(orderEntity));
    }

    @Override
    public void cancelOrder(String id) {
        OrderEntity orderEntity = orderRepository.findById(Long.valueOf(id)).orElse(null);
        if (OrderStatusEnum.getOrderStatusEnum(orderEntity.getStatus()).equals(OrderStatusEnum.PENDING)) {
            orderEntity.setStatus(OrderStatusEnum.CANCELLED.toString());
            orderRepository.save(orderEntity);
        }else {
            throw new RuntimeException("The order can not be cancel.");
        }
    }

    @Override
    public List<OrderModel> getOrdersByCustomerId(String customerId) {
        List<OrderEntity> orderEntities = orderRepository.findByCustomerId(Long.valueOf(customerId));
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orderModels.add(orderMapper.mapEntityToModel(orderEntity));
        }
        return orderModels;
    }

    @Override
    public List<OrderModel> getOrdersByDate(LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<OrderEntity> orderEntities = orderRepository.findByDateIsBetween(dateStart, dateEnd);
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orderModels.add(orderMapper.mapEntityToModel(orderEntity));
        }
        return orderModels;
    }

    @Override
    public List<StockEntity> getStockByCustomerId(String customerId) {
        return orderRepository.customStockSearchByCustomerId(Long.valueOf(customerId));
    }

    @Override
    public boolean forceMatched(String orderId) {
        OrderEntity orderEntity = orderRepository.findById(Long.valueOf(orderId)).orElse(null);

        if (orderEntity != null) {
            if (orderEntity.getSide().equals(OrderSide.BUY.toString())){
                customerService.withdrawMoney(String.valueOf(orderEntity.getCustomer().getId()), orderEntity.getSize(), true);
                orderEntity.setStatus(OrderStatusEnum.MATCHED.toString());
            }
            if (orderEntity.getSide().equals(OrderSide.SELL.toString())){
                customerService.depositMoney(String.valueOf(orderEntity.getCustomer().getId()), orderEntity.getSize());
                orderEntity.setStatus(OrderStatusEnum.MATCHED.toString());
            }
            orderRepository.save(orderEntity);

            return true;
        }else {
            return false;
        }
    }

    public boolean fieldCheck(OrderModel orderModel) {
        return customerService.isExistCustomer(String.valueOf(orderModel.getCustomer().getId())) && stockService.isStockExist(String.valueOf(orderModel.getStock().getId())) && !orderModel.getSide().isEmpty();
    }


}
