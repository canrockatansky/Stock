package com.example.ing.business.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderModel {
    private int id;
    private BigDecimal size;
    private String side;
    private String status;
    private LocalDateTime date;
    private CustomerModel customer;
    private StockModel stock;

    public OrderModel(int id, BigDecimal size, String side, String status, LocalDateTime date, CustomerModel customer, StockModel stock) {
        this.id = id;
        this.size = size;
        this.side = side;
        this.status = status;
        this.date = date;
        this.customer = customer;
        this.stock = stock;
    }

    public OrderModel() {
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

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public StockModel getStock() {
        return stock;
    }

    public void setStock(StockModel stock) {
        this.stock = stock;
    }
}
