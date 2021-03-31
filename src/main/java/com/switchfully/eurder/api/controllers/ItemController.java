package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.item.CreateItemDTO;
import com.switchfully.eurder.api.dtos.item.GetItemDto;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final AuthorizationService authorizationService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService, AuthorizationService authorizationService) {
        this.itemService = itemService;
        this.authorizationService = authorizationService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestHeader(value = "Authorization", required = false) String authorizationId, @RequestBody CreateItemDTO createItemDTO) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotAdmin(authorizationId);
        logger.info("An admin with ID " + authorizationId + " created an item with ID " + itemService.createItem(createItemDTO));
    }

    @GetMapping(produces = "application/json")
    public List<GetItemDto> getItems() {
        return itemService.getAllItemsDto();
    }
}
