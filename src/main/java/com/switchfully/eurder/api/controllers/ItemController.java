package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.item.CreateItemDTO;
import com.switchfully.eurder.api.dtos.mappers.ItemDtoMapper;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final AuthorizationService authorizationService;

    public ItemController(ItemService itemService, AuthorizationService authorizationService) {
        this.itemService = itemService;
        this.authorizationService = authorizationService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestHeader(value = "Authorization", required = false) String userId, @RequestBody CreateItemDTO createItemDTO) throws IllegalAccessException {
        authorizationService.throwExceptionIfNotAdmin(userId);
        itemService.createItem(ItemDtoMapper.mapCreateItemDtoToItem(createItemDTO));
    }
}
