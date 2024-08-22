package com.example.ing.utils.mapper;

import com.example.ing.api.model.CustomerViewModel;
import com.example.ing.business.model.CustomerModel;
import com.example.ing.persistence.model.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {
    public abstract CustomerModel mapViewToModel(CustomerViewModel customerViewModel);
    public abstract CustomerViewModel mapModelToView(CustomerModel customerModel);
    public abstract CustomerEntity mapModelToEntity(CustomerModel customerModel);
    public abstract CustomerModel mapEntityToModel(CustomerEntity customerEntity);
}
