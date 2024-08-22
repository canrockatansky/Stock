package com.example.ing.api.controller;

import com.example.ing.api.model.OrderViewModel;
import com.example.ing.business.model.OrderModel;
import com.example.ing.business.service.OrderService;
import com.example.ing.utils.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public OrderViewModel create(@RequestBody OrderViewModel orderViewModel) {
        OrderModel orderModel = orderService.createOrder(orderMapper.mapViewToModel(orderViewModel));
        return orderMapper.mapModelToView(orderModel);
    }

    @PutMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public OrderViewModel update(@RequestBody OrderViewModel orderViewModel) {
        OrderModel orderModel = orderService.updateOrder(orderMapper.mapViewToModel(orderViewModel));
        return orderMapper.mapModelToView(orderModel);
    }

    @DeleteMapping("/cancel")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void cancel(@PathVariable String id) {
        orderService.cancelOrder(id);
    }

    @GetMapping("/getByGustomerId/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<OrderViewModel> getByCustomer(@PathVariable String customerId) {
        List<OrderModel> orderModels = orderService.getOrdersByCustomerId(customerId);
        List<OrderViewModel> orderViewModels = new ArrayList<>();
        orderModels.forEach(orderModel ->
                orderViewModels.add(orderMapper.mapModelToView(orderModel))
        );
        return orderViewModels;
    }

    @GetMapping("/getByDate/{startDate}/{endDate}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<OrderViewModel> getByDate(@PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        List<OrderModel> orderModels = orderService.getOrdersByDate(startDate, endDate);
        List<OrderViewModel> orderViewModels = new ArrayList<>();
        orderModels.forEach(orderModel ->orderViewModels.add(orderMapper.mapModelToView(orderModel)));
        return orderViewModels;
    }

    @PostMapping("/forceMatched/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public boolean forceMatched(@PathVariable String id) {
        return orderService.forceMatched(id);
    }
}
