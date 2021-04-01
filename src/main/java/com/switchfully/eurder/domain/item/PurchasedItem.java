package com.switchfully.eurder.domain.item;

import java.util.UUID;

public class PurchasedItem {
    private final UUID id;
    private final String name;
    private final String description;
    private final Price pricePerUnit;

    public PurchasedItem(UUID id, String name, String description, Price pricePerUnit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
    }

    public PurchasedItem(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.pricePerUnit = item.getPricePerUnit();
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
}
