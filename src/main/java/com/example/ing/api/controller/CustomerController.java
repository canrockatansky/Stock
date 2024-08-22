package com.example.ing.api.controller;

import com.example.ing.api.model.CustomerViewModel;
import com.example.ing.business.model.CustomerModel;
import com.example.ing.business.service.CustomerService;
import com.example.ing.utils.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerViewModel create(@RequestBody CustomerViewModel customerViewModel) {
        CustomerModel customerModel = customerService.createCustomer(customerMapper.mapViewToModel(customerViewModel));
        return customerMapper.mapModelToView(customerModel);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerViewModel update(@RequestBody CustomerViewModel customerViewModel) {
        CustomerModel customerModel = customerService.updateCustomer(customerMapper.mapViewToModel(customerViewModel));
        return customerMapper.mapModelToView(customerModel);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void delete(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerViewModel get(@PathVariable String id) {
        return customerMapper.mapModelToView(customerService.getCustomer(id));
    }

    @PostMapping("/withdraw/{id}/{amount}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerViewModel withdrawMoney(@PathVariable String id, @PathVariable BigDecimal amount, @PathVariable Boolean isForced) {
        CustomerModel customerModel = customerService.withdrawMoney(id, amount, isForced == null ? false : isForced);
        return customerMapper.mapModelToView(customerModel);
    }

    @PostMapping("/deposit/{id}/{amount}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerViewModel depositMoney(@PathVariable String id, @PathVariable BigDecimal amount) {
        CustomerModel customerModel = customerService.depositMoney(id, amount);
        return customerMapper.mapModelToView(customerModel);
    }
}
