package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.order.GetItemGroupShippingDTO;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;
    private final AuthorizationService authorizationService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ShippingController(ShippingService shippingService, AuthorizationService authorizationService) {
        this.shippingService = shippingService;
        this.authorizationService = authorizationService;
    }

    @GetMapping(path = "/date/today")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<GetItemGroupShippingDTO> getTodayShipments(@RequestHeader(name = "Authorization", required = false) String authorizationId) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotAdmin(authorizationId);
        logger.info("An admin with ID " + authorizationId + " requested a list of all items shipping today");
        return shippingService.getOrdersShippingTodayDto();
    }
}
