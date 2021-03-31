package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final AuthorizationService authorizationService;

    public OrderController(OrderService orderService, AuthorizationService authorizationService) {
        this.orderService = orderService;
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public Map<UUID, Order> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public GetPriceDTO placeOrder(@RequestBody CreateOrderDTO createOrderDTO, @RequestHeader(value = "Authorization", required = false) String userId) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotMember(userId);
        return orderService.placeOrder(createOrderDTO, userId);
    }
}
