package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.service.AuthenticationService;
import com.switchfully.eurder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public OrderController(OrderService orderService, AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORDER_ITEM')")
    public GetPriceDTO placeOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        logger.info("A customer with ID " + authenticationService.getAuthenticatedUser().getId() + " requested to place an order");
        return orderService.placeOrder(createOrderDTO,authenticationService.getAuthenticatedUser());
    }
}
