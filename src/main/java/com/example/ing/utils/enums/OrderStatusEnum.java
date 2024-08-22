package com.example.ing.utils.enums;

public enum OrderStatusEnum {
    MATCHED, PENDING, CANCELLED;

    public static OrderStatusEnum getOrderStatusEnum(String status) {
        return OrderStatusEnum.valueOf(status);
    }
}
