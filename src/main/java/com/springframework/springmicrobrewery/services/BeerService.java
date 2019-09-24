package com.springframework.springmicrobrewery.services;

import com.springframework.springmicrobrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
