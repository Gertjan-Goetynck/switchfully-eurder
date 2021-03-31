package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.customer.CreateCustomerDTO;
import com.switchfully.eurder.api.dtos.mappers.CustomerDtoMapper;
import com.switchfully.eurder.domain.user.Customer;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.domain.user.UserRepository;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final UserRepository userRepository;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createCustomer(CreateCustomerDTO createCustomerDTO) {
        userRepository.addUser(CustomerDtoMapper.mapCreateCustomerDtoToCustomer(createCustomerDTO));
    }

    public User getCustomerById(String id) {
       return userRepository.getUserById(ValidationUtil.convertStringToUUID(id));
    }
}
