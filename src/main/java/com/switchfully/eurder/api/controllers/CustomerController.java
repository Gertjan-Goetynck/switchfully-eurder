package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.customer.CreateCustomerDTO;
import com.switchfully.eurder.api.dtos.customer.GetCustomerDTO;
import com.switchfully.eurder.api.dtos.order.GetOrderHistoryDTO;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.CustomerService;
import com.switchfully.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final AuthorizationService authorizationService;

    public CustomerController(CustomerService customerService, OrderService orderService, AuthorizationService authorizationService) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.authorizationService = authorizationService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        customerService.createCustomer(createCustomerDTO);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<GetCustomerDTO> getAllCustomers(@RequestHeader(name = "Authorization", required = false) String authorizationId) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotAdmin(authorizationId);
        return customerService.getAllCustomersDto();
    }

    @GetMapping(path = "/{customerId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GetCustomerDTO getCustomerById(@RequestHeader(name = "Authorization", required = false) String authorizationId, @PathVariable String customerId) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotAdmin(authorizationId);
        return customerService.getCustomerByIdDto(customerId);
    }

    @GetMapping(path = "/{customerId}/orders", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GetOrderHistoryDTO getOrderHistoryByCustomer(@RequestHeader(name = "Authorization", required = false) String authorizationId, @PathVariable String customerId) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotOwnProfile(authorizationId, customerId);
        return orderService.getOrderHistoryByCustomerDto(customerId);
    }

}
