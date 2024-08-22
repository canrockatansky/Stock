package com.example.ing.utils.mapper;

import com.example.ing.api.model.OrderViewModel;
import com.example.ing.business.model.OrderModel;
import com.example.ing.persistence.model.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    public abstract OrderModel mapViewToModel(OrderViewModel orderViewModel);
    public abstract OrderViewModel mapModelToView(OrderModel orderModel);
    public abstract OrderEntity mapModelToEntity(OrderModel orderModel);
    public abstract OrderModel mapEntityToModel(OrderEntity orderEntity);
}
