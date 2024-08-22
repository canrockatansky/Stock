package com.example.ing.utils.mapper;

import com.example.ing.api.model.StockViewModel;
import com.example.ing.business.model.StockModel;
import com.example.ing.persistence.model.StockEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StockMapper {
    public abstract StockModel mapViewToModel(StockViewModel stockViewModel);
    public abstract StockViewModel mapModelToView(StockModel stockModel);
    public abstract StockEntity mapModelToEntity(StockModel stockModel);
    public abstract StockModel mapEntityToModel(StockEntity stockEntity);
}
