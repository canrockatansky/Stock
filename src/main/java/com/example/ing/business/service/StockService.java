package com.example.ing.business.service;

import com.example.ing.business.model.StockModel;

import java.util.List;

public interface StockService {
    StockModel createStock(StockModel stockModel);
    StockModel updateStock(StockModel stockModel);
    void deleteStock(String id);
    List<StockModel> getStocksByCustomerId(String customerId);
    StockModel getStockById(String id);
    boolean isStockExist(String id);
}
