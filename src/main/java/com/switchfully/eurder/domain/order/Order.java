package com.switchfully.eurder.domain.order;

import com.switchfully.eurder.domain.item.Price;
import com.switchfully.eurder.domain.order.itemgroup.ItemGroup;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<ItemGroup> itemGroupList;
    private final User purchaser;

    public Order(List<ItemGroup> itemGroupList, User purchaser) {
        this.id = UUID.randomUUID();
        ValidationUtil.throwExceptionIfNullObject(itemGroupList,"Item group");
        this.itemGroupList = itemGroupList;
        ValidationUtil.throwExceptionIfNullObject(purchaser,"The purchaser");
        this.purchaser = purchaser;
    }

    public Price calculateOrderPrice() {
        return itemGroupList.stream()
                .map(ItemGroup::calculateItemGroupPrice)
                .reduce((itemGroupItem1, itemGroupItem2) -> new Price(itemGroupItem1.getAmount() + itemGroupItem2.getAmount(), itemGroupItem1.getCurrency()))
                .orElseThrow();
    }

    public UUID getId() {
        return id;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public User getPurchaser() {
        return purchaser;
    }
}
