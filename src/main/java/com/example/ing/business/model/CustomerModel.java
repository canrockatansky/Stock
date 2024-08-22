package com.example.ing.business.model;

import java.math.BigDecimal;

public class CustomerModel {
    private int id;
    private String name;
    private String surname;
    private BigDecimal amount;

    public CustomerModel() {
    }

    public CustomerModel(int id, String name, String surname, BigDecimal amount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
