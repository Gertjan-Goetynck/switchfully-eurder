package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.customer.CreateCustomerDTO;
import com.switchfully.eurder.api.dtos.customer.GetCustomerDTO;
import com.switchfully.eurder.domain.user.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerDtoMapper {
    public static Customer mapCreateCustomerDtoToCustomer(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.getFirstName(), createCustomerDTO.getLastName(), createCustomerDTO.getEmail(), createCustomerDTO.getPassword(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
    }

    public static GetCustomerDTO mapCustomerToGetCustomerDto(Customer customer) {
        return new GetCustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    public static List<GetCustomerDTO> mapCustomerListToGetCustomerDtoList(List<Customer> customerList) {
        return customerList.stream()
                .map(CustomerDtoMapper::mapCustomerToGetCustomerDto)
                .collect(Collectors.toList());

    }
}
