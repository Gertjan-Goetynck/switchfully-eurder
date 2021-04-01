package com.switchfully.eurder.domain.order.itemgroup;

import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.item.Price;
import com.switchfully.eurder.domain.item.PurchasedItem;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

import java.time.LocalDate;

public class ItemGroup {
    private final PurchasedItem purchasedItem;
    private final int amountOfItems;
    private final LocalDate shippingDate;

    public ItemGroup(Item item, int amountOfItems) {
        ValidationUtil.throwExceptionIfNullObject(item, "The item");
        this.purchasedItem = new PurchasedItem(item.getId(), item.getName(), item.getDescription(), item.getPricePerUnit());
        ValidationUtil.throwExceptionIfNegativeNumber(amountOfItems, "The amount of items");
        this.amountOfItems = amountOfItems;
        this.shippingDate = calculateShippingDateBasedOnStock(item.getAmountInStock(), amountOfItems);
    }

    private static LocalDate calculateShippingDateBasedOnStock(int itemsInStock, int amountOfItems) {
        if (amountOfItems > itemsInStock) {
            return LocalDate.now().plusDays(7);
        }
        return LocalDate.now().plusDays(1);
    }

    public Price calculateItemGroupPrice() {
        return new Price(purchasedItem.getPricePerUnit().getAmount() * amountOfItems, purchasedItem.getPricePerUnit().getCurrency());
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public PurchasedItem getPurchasedItem() {
        return purchasedItem;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }
}
