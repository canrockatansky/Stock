package com.example.ing.api.model;

import com.example.ing.utils.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderViewModel {

    private int id;
    private BigDecimal size;
    private String side;
    private String status = OrderStatusEnum.PENDING.toString();
    private LocalDateTime date = LocalDateTime.now();
    private CustomerViewModel customer;
    private StockViewModel stock;

    public OrderViewModel() {
    }

    public OrderViewModel(int id, BigDecimal size, String side, String status, LocalDateTime date, CustomerViewModel customer, StockViewModel stock) {
        this.id = id;
        this.size = size;
        this.side = side;
        this.status = status;
        this.date = date;
        this.customer = customer;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public CustomerViewModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerViewModel customer) {
        this.customer = customer;
    }

    public StockViewModel getStock() {
        return stock;
    }

    public void setStock(StockViewModel stock) {
        this.stock = stock;
    }
}
