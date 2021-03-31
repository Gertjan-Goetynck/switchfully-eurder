package com.switchfully.eurder.api.dtos.item;

import com.switchfully.eurder.domain.item.Price;

import java.util.UUID;

public class GetItemDto {
    private final UUID id;
    private final String name;
    private final String description;
    private final Price pricePerUnit;
    private final int amountInStock;

    public GetItemDto(UUID id, String name, String description, Price pricePerUnit, int amountInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
        this.amountInStock = amountInStock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Price getPricePerUnit() {
        return pricePerUnit;
    }

    public int getAmountInStock() {
        return amountInStock;
    }
}
