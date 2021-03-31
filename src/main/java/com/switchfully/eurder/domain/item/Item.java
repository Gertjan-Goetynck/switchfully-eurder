package com.switchfully.eurder.domain.item;

import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private final Price pricePerUnit;
    private int amountInStock;
    private StockUrgency stockUrgency;

    public Item(String name, String description, Price pricePerUnit, int amountInStock) {
        this.id = UUID.randomUUID();
        ValidationUtil.throwExceptionIfBlankOrNullString(name, "Item name");
        this.name = name;
        ValidationUtil.throwExceptionIfBlankOrNullString(description, "Item description");
        this.description = description;
        ValidationUtil.throwExceptionIfNullObject(pricePerUnit, "Price per unit");
        this.pricePerUnit = pricePerUnit;
        ValidationUtil.throwExceptionIfNegativeNumber(amountInStock, "Amount in stock");
        setAmountInStock(amountInStock);
    }

    public void sellItem(int amount) {
        if (amountInStock <= amount) {
            setAmountInStock(0);
        } else {
            setAmountInStock(amountInStock - amount);
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

    public StockUrgency getStockUrgency() {
        return stockUrgency;
    }

    private void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        if (amountInStock < 5) {
            this.stockUrgency = StockUrgency.STOCK_LOW;
        } else if (amountInStock < 10) {
            this.stockUrgency = StockUrgency.STOCK_MEDIUM;
        } else {
            this.stockUrgency = StockUrgency.STOCK_HIGH;
        }
    }
}
