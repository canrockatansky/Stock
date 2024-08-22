package com.example.ing.business.model;

import java.math.BigDecimal;
import java.util.List;

public class StockModel {
    private int id;
    private String asset;
    private BigDecimal price;

    public StockModel() {
    }

    public StockModel(int id, String asset, BigDecimal price) {
        this.id = id;
        this.asset = asset;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
