package com.switchfully.eurder.domain.order;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private final Map<UUID, Order> orderMap;

    public OrderRepository() {
        this.orderMap = new HashMap<>();
    }

    public Order addOrder(Order order) {
        orderMap.put(order.getId(), order);
        return orderMap.get(order.getId());
    }

    public Map<UUID, Order> getAll() {
        return orderMap;
    }

    public List<Order> getOrdersByUser(UUID userId) {
        return orderMap.values().stream()
                .filter(order -> order.getCustomer().getId().equals(userId))
                .collect(Collectors.toList());
    }
}
