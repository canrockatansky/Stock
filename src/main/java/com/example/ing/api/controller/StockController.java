package com.example.ing.api.controller;

import com.example.ing.api.model.StockViewModel;
import com.example.ing.business.model.StockModel;
import com.example.ing.business.service.StockService;
import com.example.ing.utils.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockMapper stockMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public StockViewModel create(@RequestBody StockViewModel stockViewModel) {
        StockModel stockModel = stockService.createStock(stockMapper.mapViewToModel(stockViewModel));
        return stockMapper.mapModelToView(stockModel);
    }

    @PutMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public StockViewModel update(@RequestBody StockViewModel stockViewModel) {
        StockModel stockModel = stockService.updateStock(stockMapper.mapViewToModel(stockViewModel));
        return stockMapper.mapModelToView(stockModel);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void delete(@PathVariable String id) {
        stockService.deleteStock(id);
    }

    @GetMapping("/getByCustomerId/{customerId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<StockViewModel> getByCustomerId(@PathVariable String customerId) {
        List<StockModel> stockModels = stockService.getStocksByCustomerId(customerId);
        List<StockViewModel> stockViewModels = new ArrayList<>();
        for (StockModel stockModel : stockModels) {
            stockViewModels.add(stockMapper.mapModelToView(stockModel));
        }

        return stockViewModels;
    }
}
