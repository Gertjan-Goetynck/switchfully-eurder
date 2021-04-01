package com.switchfully.eurder.api.dtos.order;

import com.switchfully.eurder.domain.item.Price;
import com.switchfully.eurder.domain.item.PurchasedItem;

public class GetItemGroupShippingDTO {
    private final PurchasedItem purchasedItem;
    private final int amount;
    private final Price itemGroupPrice;
    private final String address;

    public GetItemGroupShippingDTO(PurchasedItem purchasedItem, int amount, Price itemGroupPrice, String address) {
        this.purchasedItem = purchasedItem;
        this.amount = amount;
        this.itemGroupPrice = itemGroupPrice;
        this.address = address;
    }

    public PurchasedItem getPurchasedItem() {
        return purchasedItem;
    }

    public int getAmount() {
        return amount;
    }

    public Price getItemGroupPrice() {
        return itemGroupPrice;
    }

    public String getAddress() {
        return address;
    }
}
