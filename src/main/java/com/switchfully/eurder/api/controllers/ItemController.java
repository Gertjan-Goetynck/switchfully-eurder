package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.item.CreateItemDTO;
import com.switchfully.eurder.api.dtos.item.GetItemDto;
import com.switchfully.eurder.api.dtos.item.UpdateItemDTO;
import com.switchfully.eurder.service.AuthenticationService;
import com.switchfully.eurder.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService, AuthenticationService authenticationService) {
        this.itemService = itemService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADD_ITEM')")
    public void createItem(@RequestBody CreateItemDTO createItemDTO)   {
        logger.info("A user with ID " + authenticationService.getAuthenticatedUser().getId() + " created an item with ID " + itemService.createItem(createItemDTO));
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('VIEW_ITEM_OVERVIEW')")
    public List<GetItemDto> getItems(@RequestParam(required = false) String stockUrgency)   {
        logger.info("A user with ID " + authenticationService.getAuthenticatedUser().getId() + " requested a list of all items");
        return itemService.getAllItemsDtoSortedByStock(stockUrgency);
    }

    @PutMapping(path = "/{itemId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('UPDATE_ITEM')")
    public void updateItem( @RequestBody UpdateItemDTO updateItemDTO, @PathVariable String itemId)  {
        logger.info("A user with ID " + authenticationService.getAuthenticatedUser().getId() + " updated an item with ID " + itemId);
        itemService.updateItem(updateItemDTO, itemId);
    }

}
