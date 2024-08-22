package com.example.ing.utils.enums;

public enum OrderSide {
    BUY, SELL;

    public static OrderSide getOrderSide(String orderSide) {
        return OrderSide.valueOf(orderSide.toUpperCase());
    }
}
