package com.switchfully.eurder.domain.item;

import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

import java.util.UUID;

public class Item {
    private final UUID id;
    private String name;
    private String description;
    private Price pricePerUnit;
    private int amountInStock;
    private StockUrgency stockUrgency;

    public Item(String name, String description, Price pricePerUnit, int amountInStock) {
        this.id = UUID.randomUUID();
        setName(name);
        setDescription(description);
        setPricePerUnit(pricePerUnit);
        setAmountInStock(amountInStock);
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

    public Item setName(String name) {
        ValidationUtil.throwExceptionIfBlankOrNullString(name, "Item name");
        this.name = name;
        return this;
    }

    public Item setDescription(String description) {
        ValidationUtil.throwExceptionIfBlankOrNullString(description, "Item description");
        this.description = description;
        return this;
    }

    public Item setPricePerUnit(Price pricePerUnit) {
        ValidationUtil.throwExceptionIfNullObject(pricePerUnit, "Price per unit");
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public Item setAmountInStock(int amountInStock) {
        ValidationUtil.throwExceptionIfNegativeNumber(amountInStock, "Amount in stock");
        this.amountInStock = amountInStock;
        setStockUrgency(amountInStock);
        return this;
    }

    private void setStockUrgency(int amountInStock) {
        if (amountInStock < 5) {
            this.stockUrgency = StockUrgency.STOCK_LOW;
        } else if (amountInStock < 10) {
            this.stockUrgency = StockUrgency.STOCK_MEDIUM;
        } else {
            this.stockUrgency = StockUrgency.STOCK_HIGH;
        }
    }
}
