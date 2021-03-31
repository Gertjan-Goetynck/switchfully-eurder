package com.switchfully.eurder.domain.item;

import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private final Price pricePerUnit;
    private int amountInStock;

    public Item(String name, String description, Price pricePerUnit, int amountInStock) {
        this.id = UUID.randomUUID();
        ValidationUtil.throwExceptionIfBlankOrNullString(name, "Item name");
        this.name = name;
        ValidationUtil.throwExceptionIfBlankOrNullString(description, "Item description");
        this.description = description;
        ValidationUtil.throwExceptionIfNullObject(pricePerUnit, "Price per unit");
        this.pricePerUnit = pricePerUnit;
        ValidationUtil.throwExceptionIfNegativeNumber(amountInStock, "Amount in stock");
        this.amountInStock = amountInStock;
    }

    public void sellItem(int amount) {
        if (amountInStock <= amount) {
            amountInStock = 0;
        } else {
            amountInStock -= amount;
        }
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
