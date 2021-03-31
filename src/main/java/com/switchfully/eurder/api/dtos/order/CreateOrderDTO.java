package com.switchfully.eurder.api.dtos.order;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTO {
    private final List<CreateItemGroupDTO> itemList;

    public CreateOrderDTO() {
        itemList = new ArrayList<>();
    }

    public CreateOrderDTO(List<CreateItemGroupDTO> itemList) {
        this.itemList = itemList;
    }

    public List<CreateItemGroupDTO> getItemList() {
        return itemList;
    }
}
