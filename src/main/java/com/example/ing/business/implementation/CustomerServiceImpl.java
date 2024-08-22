package com.example.ing.business.implementation;

import com.example.ing.business.model.CustomerModel;
import com.example.ing.business.service.CustomerService;
import com.example.ing.persistence.model.CustomerEntity;
import com.example.ing.persistence.repository.CustomerRepository;
import com.example.ing.utils.mapper.CustomerMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerModel createCustomer(CustomerModel customerModel) {
        CustomerEntity customerEntity = customerMapper.mapModelToEntity(customerModel);
        return customerMapper.mapEntityToModel(customerRepository.save(customerEntity));
    }

    @Override
    public CustomerModel updateCustomer(CustomerModel customerModel) {
        CustomerEntity customerEntity = customerMapper.mapModelToEntity(customerModel);
        return customerMapper.mapEntityToModel(customerRepository.save(customerEntity));
    }

    @Override
    public void deleteCustomer(String id) {
        CustomerEntity customerEntity = customerRepository.findById(Long.valueOf(id)).orElse(null);
        customerRepository.delete(customerEntity);
    }

    @Override
    public CustomerModel getCustomer(String id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(Long.valueOf(id));
        return customerEntity.map(entity -> customerMapper.mapEntityToModel(entity)).orElse(null);
    }

    @Transactional
    @Override
    public CustomerModel withdrawMoney(String id, BigDecimal amount, boolean isForced) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(Long.valueOf(id));

        if (customerEntity.isPresent()) {
            if (isForced || amount.compareTo(customerEntity.get().getAmount()) < BigDecimal.ZERO.intValue()){
                customerEntity.get().setAmount(customerEntity.get().getAmount().subtract(amount));
                return customerMapper.mapEntityToModel(customerRepository.save(customerEntity.get()));
            }
            else{
                throw new RuntimeException("Not enough money to withdraw");
            }
        }
        else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Transactional
    @Override
    public CustomerModel depositMoney(String id, BigDecimal amount) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(Long.valueOf(id));

        if (customerEntity.isPresent()) {
            customerEntity.get().setAmount(customerEntity.get().getAmount().add(amount));
            return customerMapper.mapEntityToModel(customerRepository.save(customerEntity.get()));
        }
        else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public boolean isExistCustomer(String id) {
        return customerRepository.existsById(Long.valueOf(id));
    }
}
