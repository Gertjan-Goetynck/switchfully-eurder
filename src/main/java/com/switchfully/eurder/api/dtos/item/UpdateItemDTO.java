package com.switchfully.eurder.api.dtos.item;

import com.switchfully.eurder.domain.item.Price;

public class UpdateItemDTO {
    private final String name;
    private final String description;
    private final Price pricePerUnit;
    private final int amountInStock;

    public UpdateItemDTO(String name, String description, Price pricePerUnit, int amountInStock) {
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
        this.amountInStock = amountInStock;
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
