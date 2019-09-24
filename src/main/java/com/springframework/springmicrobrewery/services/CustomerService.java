package com.springframework.springmicrobrewery.services;

import com.springframework.springmicrobrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
