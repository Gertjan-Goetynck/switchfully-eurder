package com.switchfully.eurder.api.dtos.order;

import com.switchfully.eurder.domain.item.Price;

public class GetItemGroupDTO {
    private final String name;
    private final int amount;
    private final Price itemGroupPrice;

    public GetItemGroupDTO(String name, int amount, Price itemGroupPrice) {
        this.name = name;
        this.amount = amount;
        this.itemGroupPrice = itemGroupPrice;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Price getItemGroupPrice() {
        return itemGroupPrice;
    }
}
