package com.example.ing.business.service;

import com.example.ing.business.model.OrderModel;
import com.example.ing.business.model.StockModel;
import com.example.ing.persistence.model.StockEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderModel createOrder(OrderModel orderModel);
    OrderModel updateOrder(OrderModel orderModel);
    void cancelOrder(String id);
    List<OrderModel> getOrdersByCustomerId(String customerId);
    List<OrderModel> getOrdersByDate(LocalDateTime dateStart, LocalDateTime dateEnd);
    List<StockEntity> getStockByCustomerId(String customerId);
    boolean forceMatched(String orderId);
}
