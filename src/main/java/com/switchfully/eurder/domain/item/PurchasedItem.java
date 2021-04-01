package com.switchfully.eurder.domain.item;

public class PurchasedItem {
    private final String name;
    private final String description;
    private final Price pricePerUnit;

    public PurchasedItem(String name, String description, Price pricePerUnit) {
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
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
}
