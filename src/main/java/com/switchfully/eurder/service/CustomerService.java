package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.customer.CreateCustomerDTO;
import com.switchfully.eurder.api.dtos.customer.GetCustomerDTO;
import com.switchfully.eurder.api.dtos.mappers.CustomerDtoMapper;
import com.switchfully.eurder.domain.user.Customer;
import com.switchfully.eurder.domain.user.UserRepository;
import com.switchfully.eurder.infrastructure.exceptions.UserNotFoundException;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = CustomerDtoMapper.mapCreateCustomerDtoToCustomer(createCustomerDTO);
        userRepository.addUser(customer);
        return customer.getId();
    }

    public List<GetCustomerDTO> getAllCustomersDto() {
        return CustomerDtoMapper.mapCustomerListToGetCustomerDtoList(userRepository.getAllCustomers());
    }

    public Customer getCustomerById(String userId) {
        return userRepository.getCustomerById(ValidationUtil.convertStringToUUID(userId));
    }

    public GetCustomerDTO getCustomerByIdDto(String userId) {
        if(!isUser(userId)){
            logger.warn("User with ID " + userId + " not found");
            throw new UserNotFoundException(UUID.fromString(userId));
        }
        return CustomerDtoMapper.mapCustomerToGetCustomerDto(getCustomerById(userId));
    }
    private boolean isUser(String userId) {
        return userRepository.getUserById(UUID.fromString(userId)) != null;
    }

}
