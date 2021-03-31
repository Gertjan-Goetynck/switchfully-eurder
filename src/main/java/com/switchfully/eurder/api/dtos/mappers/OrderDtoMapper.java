package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.api.dtos.order.GetOrderDTO;
import com.switchfully.eurder.api.dtos.order.GetOrderHistoryDTO;
import com.switchfully.eurder.domain.item.Currency;
import com.switchfully.eurder.domain.item.Price;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.domain.order.itemgroup.ItemGroup;
import com.switchfully.eurder.domain.user.Customer;
import com.switchfully.eurder.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDtoMapper {
    public static Order mapCreateOrderDtoToOrder(CreateOrderDTO createOrderDTO, Customer customer, ItemService itemService) {

        List<ItemGroup> itemGroupList = ItemGroupDtoMapper.mapCreateItemGroupDtoListToItemGroupList(createOrderDTO.getItemList(), itemService);

        return new Order(itemGroupList, customer);
    }

    public static GetOrderHistoryDTO mapOrderListToGetOrderHistoryDto(List<Order> ordersByUser) {
        return new GetOrderHistoryDTO(mapOrderListToGetOrderDtoList(ordersByUser),
                getTotalPriceFromOrderList(ordersByUser));
    }

    private static Price getTotalPriceFromOrderList(List<Order> orders) {
        return orders.stream()
                .map(Order::calculateOrderPrice)
                .reduce((orderPrice1, orderPrice2) ->
                        new Price(orderPrice1.getAmount() + orderPrice2.getAmount(), orderPrice1.getCurrency())
                ).orElse(new Price(0, Currency.EUR));
    }

    public static GetOrderDTO mapOrderToGetOrderDto(Order order) {
        return new GetOrderDTO(order.getId(), ItemGroupDtoMapper.mapItemGroupListToGetItemGroupDtoList(order.getItemGroupList()), order.calculateOrderPrice());
    }

    public static List<GetOrderDTO> mapOrderListToGetOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(OrderDtoMapper::mapOrderToGetOrderDto)
                .collect(Collectors.toList());
    }
}
