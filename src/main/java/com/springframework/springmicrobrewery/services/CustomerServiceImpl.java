package com.springframework.springmicrobrewery.services;

import com.springframework.springmicrobrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .customerName("Anil Mirge")
                .build();
    }
}
