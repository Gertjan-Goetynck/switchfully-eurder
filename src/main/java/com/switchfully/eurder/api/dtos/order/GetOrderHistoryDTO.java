package com.switchfully.eurder.api.dtos.order;

import com.switchfully.eurder.domain.item.Price;

import java.util.List;

public class GetOrderHistoryDTO {
    private final List<GetOrderDTO> orders;
    private final Price totalPrice;

    public GetOrderHistoryDTO(List<GetOrderDTO> orders, Price totalPrice) {
        this.orders = orders;
        this.totalPrice = totalPrice;
    }

    public List<GetOrderDTO> getOrders() {
        return orders;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }
}
