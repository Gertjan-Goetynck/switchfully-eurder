package com.switchfully.eurder.api.dtos.item;

import com.switchfully.eurder.domain.item.Price;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

import java.util.UUID;

public class CreateItemDTO {
    private final String name;
    private final String description;
    private final Price pricePerUnit;
    private final int amountInStock;

    public CreateItemDTO(String name, String description, Price pricePerUnit, Integer amountInStock) {
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
        ValidationUtil.throwExceptionIfNullObject(amountInStock,"Amount in stock");
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
