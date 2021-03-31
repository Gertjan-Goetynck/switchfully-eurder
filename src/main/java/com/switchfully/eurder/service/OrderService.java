package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.mappers.OrderDtoMapper;
import com.switchfully.eurder.api.dtos.mappers.PriceDtoMapper;
import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.domain.order.OrderRepository;
import com.switchfully.eurder.infrastructure.exceptions.ItemNotFoundException;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, ItemService itemService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
        this.customerService = customerService;
    }

    public GetPriceDTO placeOrder(CreateOrderDTO createOrderDTO, String userId) {
        updateStock(createOrderDTO);
        return PriceDtoMapper.mapPriceToGetPriceDto(orderRepository.addOrder(OrderDtoMapper.mapCreateOrderDtoToOrder(createOrderDTO, customerService.getCustomerById(userId), itemService))
                .calculateOrderPrice());
    }

    private void updateStock(CreateOrderDTO createOrderDTO) {
        createOrderDTO.getItemList()
                .forEach(itemGroup -> {
                    if (ValidationUtil.isNullObject(itemService.getItemById(itemGroup.getItemId()))) {
                        throw new ItemNotFoundException();
                    }
                    itemService.getItemById(itemGroup.getItemId()).sellItem(itemGroup.getAmountOfItems());
                });
    }

    public Map<UUID, Order> getAllOrders() {
        return orderRepository.getAll();
    }
}
