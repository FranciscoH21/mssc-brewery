package io.thinkingcode.msscbrewery.web.mappers;

import io.thinkingcode.msscbrewery.domain.Customer;
import io.thinkingcode.msscbrewery.web.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
