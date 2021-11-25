package io.thinkingcode.msscbrewery.services;

import io.thinkingcode.msscbrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {

    CustomerDTO getCustomerById(UUID customerId);
}
