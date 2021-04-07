package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.order.GetItemGroupShippingDTO;
import com.switchfully.eurder.service.AuthenticationService;
import com.switchfully.eurder.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;
    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ShippingController(ShippingService shippingService, AuthenticationService authenticationService) {
        this.shippingService = shippingService;
        this.authenticationService = authenticationService;
    }

    @GetMapping(path = "/date/today")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('VIEW_SHIPMENT_DAY')")
    public List<GetItemGroupShippingDTO> getTodayShipments() {
        logger.info("An admin with ID " + authenticationService.getAuthenticatedUser().getId() + " requested a list of all items shipping today");
        return shippingService.getOrdersShippingTodayDto();
    }
}
