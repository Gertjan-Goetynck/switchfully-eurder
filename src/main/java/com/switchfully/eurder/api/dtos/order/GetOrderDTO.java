package com.switchfully.eurder.api.dtos.order;

import com.switchfully.eurder.domain.item.Price;

import java.util.List;
import java.util.UUID;

public class GetOrderDTO {
    private final UUID orderId;
    private final List<GetItemGroupDTO> itemGroups;
    private final Price orderPrice;

    public GetOrderDTO(UUID orderId, List<GetItemGroupDTO> itemGroups, Price orderPrice) {
        this.orderId = orderId;
        this.itemGroups = itemGroups;
        this.orderPrice = orderPrice;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<GetItemGroupDTO> getItemGroups() {
        return itemGroups;
    }

    public Price getOrderPrice() {
        return orderPrice;
    }
}
