package com.switchfully.eurder.api.dtos.order;

import com.switchfully.eurder.domain.item.Item;

import java.time.LocalDate;

public class CreateItemGroupDTO {
    private final String itemId;
    private final int amountOfItems;

    public CreateItemGroupDTO(String itemId, int amountOfItems) {
        this.itemId = itemId;
        this.amountOfItems = amountOfItems;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }
}
