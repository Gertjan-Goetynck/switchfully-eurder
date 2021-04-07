package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.customer.CreateCustomerDTO;
import com.switchfully.eurder.api.dtos.customer.GetCustomerDTO;
import com.switchfully.eurder.api.dtos.order.GetOrderHistoryDTO;
import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.service.AuthenticationService;
import com.switchfully.eurder.service.CustomerService;
import com.switchfully.eurder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);


    public CustomerController(CustomerService customerService, OrderService orderService, AuthenticationService authenticationService) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        logger.info("A new customer with ID " + customerService.createCustomer(createCustomerDTO) + " was created");
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('VIEW_ALL_CUSTOMERS')")
    public List<GetCustomerDTO> getAllCustomers() {
        logger.info("An admin with ID " + authenticationService.getAuthenticatedUser().getId() + " requested a list of all customers");
        return customerService.getAllCustomersDto();
    }

    @GetMapping(path = "/{customerId}", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('VIEW_CUSTOMER')")
    public GetCustomerDTO getCustomerById(@PathVariable String customerId) {
        logger.info("An admin with ID " + authenticationService.getAuthenticatedUser().getId() + " visited the profile page of customer " + customerId);
        return customerService.getCustomerByIdDto(customerId);
    }

    @GetMapping(path = "/{customerId}/orders", produces = "application/json")
    @PreAuthorize("hasAuthority('VIEW_ORDER_HISTORY')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GetOrderHistoryDTO getOrderHistoryByCustomer(@PathVariable String customerId) throws IllegalAccessException {
        authenticationService.throwExceptionIfNotOwnProfile(customerId);
        logger.info("A user with ID " + authenticationService.getAuthenticatedUser().getId() + " requested a list of his or her orders");
        return orderService.getOrderHistoryByCustomerDto(customerId);
    }

    @PostMapping(path = "/{customerId}/orders/{orderId}")
    @PreAuthorize("hasAuthority('REDO_ORDER')")
    @ResponseStatus(HttpStatus.CREATED)
    public GetPriceDTO redoOrder(@PathVariable String customerId, @PathVariable String orderId) throws IllegalAccessException {
        authenticationService.throwExceptionIfNotOwnProfile(customerId);
        logger.info("A user with ID" + authenticationService.getAuthenticatedUser().getId() + " is redoing his order with ID" + orderId);
        return orderService.redoOrder(orderId);
    }

}
