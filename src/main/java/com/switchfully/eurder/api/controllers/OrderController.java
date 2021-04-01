package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final AuthorizationService authorizationService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public OrderController(OrderService orderService, AuthorizationService authorizationService) {
        this.orderService = orderService;
        this.authorizationService = authorizationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public GetPriceDTO placeOrder(@RequestBody CreateOrderDTO createOrderDTO, @RequestHeader(value = "Authorization", required = false) String authorizationId) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotCustomer(authorizationId);
        logger.info("An customer with ID " + authorizationId + " placed an order");
        return orderService.placeOrder(createOrderDTO, authorizationId);
    }
}
