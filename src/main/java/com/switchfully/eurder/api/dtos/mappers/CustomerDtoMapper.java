package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.customer.CreateCustomerDTO;
import com.switchfully.eurder.domain.user.Customer;

public class CustomerDtoMapper {
    public static Customer mapCreateCustomerDtoToCustomer(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.getFirstName(), createCustomerDTO.getLastName(), createCustomerDTO.getEmail(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
    }
}
