package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.order.GetItemGroupShippingDTO;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.domain.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingService {
    private final OrderRepository orderRepository;

    public ShippingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<GetItemGroupShippingDTO> getOrdersShippingTodayDto() {
        return orderRepository.getAll().values().stream()
                .map(this::getItemGroupShippingTodayDtoFromOrder)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<GetItemGroupShippingDTO> getItemGroupShippingTodayDtoFromOrder(Order order) {
        return order.getItemGroupList().stream()
                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now()))
                .map(itemGroup -> new GetItemGroupShippingDTO(itemGroup.getPurchasedItem(), itemGroup.getAmountOfItems(), itemGroup.calculateItemGroupPrice(), order.getCustomer().getAddress()))
                .collect(Collectors.toList());
    }
}
