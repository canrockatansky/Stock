package com.example.ing.business.implementation;

import com.example.ing.business.model.StockModel;
import com.example.ing.business.service.OrderService;
import com.example.ing.business.service.StockService;
import com.example.ing.persistence.model.StockEntity;
import com.example.ing.persistence.repository.StockRepository;
import com.example.ing.utils.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private OrderService orderService;

    @Override
    public StockModel createStock(StockModel stockModel) {
        StockEntity stockEntity = stockMapper.mapModelToEntity(stockModel);
        return stockMapper.mapEntityToModel(stockRepository.save(stockEntity));
    }

    @Override
    public StockModel updateStock(StockModel stockModel) {
        StockEntity stockEntity = stockMapper.mapModelToEntity(stockModel);
        return stockMapper.mapEntityToModel(stockRepository.save(stockEntity));
    }

    @Override
    public void deleteStock(String id) {
        StockEntity stockEntity = stockRepository.findById(Long.parseLong(id)).orElse(null);
        stockRepository.delete(stockEntity);
    }

    @Override
    public List<StockModel> getStocksByCustomerId(String customerId) {
        List<StockEntity> stockEntities = orderService.getStockByCustomerId(customerId);
        List<StockModel> stockModels = new ArrayList<>();
        for (StockEntity stockEntity : stockEntities) {
            stockModels.add(stockMapper.mapEntityToModel(stockEntity));
        }
        return stockModels;
    }

    @Override
    public StockModel getStockById(String id) {
        return stockMapper.mapEntityToModel(stockRepository.findById(Long.parseLong(id)).orElse(null));
    }

    @Override
    public boolean isStockExist(String id) {
        return stockRepository.existsById(Long.parseLong(id));
    }
}
