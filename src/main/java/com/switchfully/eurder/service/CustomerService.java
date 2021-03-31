package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.customer.CreateCustomerDTO;
import com.switchfully.eurder.api.dtos.customer.GetCustomerDTO;
import com.switchfully.eurder.api.dtos.mappers.CustomerDtoMapper;
import com.switchfully.eurder.api.dtos.order.GetOrderHistoryDTO;
import com.switchfully.eurder.domain.user.Customer;
import com.switchfully.eurder.domain.user.UserRepository;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final UserRepository userRepository;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createCustomer(CreateCustomerDTO createCustomerDTO) {
        userRepository.addUser(CustomerDtoMapper.mapCreateCustomerDtoToCustomer(createCustomerDTO));
    }

    public List<GetCustomerDTO> getAllCustomersDto() {
        return CustomerDtoMapper.mapCustomerListToGetCustomerDtoList(userRepository.getAllCustomers());
    }

    public Customer getCustomerById(String userId) {
        return userRepository.getCustomerById(ValidationUtil.convertStringToUUID(userId));
    }

    public GetCustomerDTO getCustomerByIdDto(String userId) {
        return CustomerDtoMapper.mapCustomerToGetCustomerDto(getCustomerById(userId));
    }
}
