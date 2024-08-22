package com.example.ing.business.service;

import com.example.ing.business.model.CustomerModel;

import java.math.BigDecimal;

public interface CustomerService {
    CustomerModel createCustomer(CustomerModel customerModel);
    CustomerModel updateCustomer(CustomerModel customerModel);
    void deleteCustomer(String id);
    CustomerModel getCustomer(String id);
    CustomerModel withdrawMoney(String id, BigDecimal amount, boolean isForced);
    CustomerModel depositMoney(String id, BigDecimal amount);
    boolean isExistCustomer(String id);
}
