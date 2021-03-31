package com.switchfully.eurder.domain.item;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {
    private final Map<UUID, Item> itemList;

    public ItemRepository() {
        itemList = new HashMap<>();
    }

    public void addItem(Item item) {
        itemList.put(item.getId(), item);
    }

    public Map<UUID, Item> getAll() {
        return itemList;
    }

    public Item getById(UUID itemId) {
        return itemList.get(itemId);
    }
}
