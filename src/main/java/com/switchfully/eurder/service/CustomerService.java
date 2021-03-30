package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.user.Customer;
import com.switchfully.eurder.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final UserRepository userRepository;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createCustomer(Customer customer) {
        userRepository.addUser(customer);
    }
}
