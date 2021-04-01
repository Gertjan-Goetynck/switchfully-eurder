package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.order.GetItemGroupShippingDTO;
import com.switchfully.eurder.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final OrderService orderService;

    public ShippingController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/date/today")
    public List<GetItemGroupShippingDTO> getTodayShipments() {
        return orderService.getOrdersShippingTodayDto();
    }
}
