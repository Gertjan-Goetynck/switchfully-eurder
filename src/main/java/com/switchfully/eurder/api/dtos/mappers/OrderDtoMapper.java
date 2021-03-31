package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.domain.order.itemgroup.ItemGroup;
import com.switchfully.eurder.domain.user.Customer;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.service.ItemService;

import java.util.List;

public class OrderDtoMapper {
    public static Order mapCreateOrderDtoToOrder(CreateOrderDTO createOrderDTO, Customer customer, ItemService itemService) {

        List<ItemGroup> itemGroupList = ItemGroupDtoMapper.mapCreateItemGroupDtoListToItemGroupList(createOrderDTO.getItemList(), itemService);

        return new Order(itemGroupList, customer);
    }
}
