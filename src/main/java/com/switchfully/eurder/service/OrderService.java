package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.mappers.ItemGroupDtoMapper;
import com.switchfully.eurder.api.dtos.mappers.OrderDtoMapper;
import com.switchfully.eurder.api.dtos.mappers.PriceDtoMapper;
import com.switchfully.eurder.api.dtos.order.CreateItemGroupDTO;
import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.api.dtos.order.GetOrderHistoryDTO;
import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.domain.order.OrderRepository;
import com.switchfully.eurder.domain.order.itemgroup.ItemGroup;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.infrastructure.exceptions.OrderNotFoundException;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public GetPriceDTO placeOrder(CreateOrderDTO createOrderDTO, User user) {
        updateStockOfItemGroupList(createOrderDTO.getItemList());
        return PriceDtoMapper.mapPriceToGetPriceDto(orderRepository.addOrder(OrderDtoMapper.mapCreateOrderDtoToOrder(createOrderDTO, customerService.getCustomerById(user.getId().toString()), itemService)).calculateOrderPrice());
    }

    public GetPriceDTO redoOrder(String orderId) {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(ItemGroupDtoMapper.mapItemGroupListToCreateItemGroupDtoList(getItemGroupListFromOrder(orderId)));
        return placeOrder(createOrderDTO, getOrderById(orderId).getCustomer());
    }

    private Order getOrderById(String orderId) {
        if (!isOrder(orderId)) {
            throw new OrderNotFoundException(UUID.fromString(orderId));
        }
        return orderRepository.getOrderById(ValidationUtil.convertStringToUUID(orderId));
    }

    private boolean isOrder(String orderId) {
        return orderRepository.getOrderById(ValidationUtil.convertStringToUUID(orderId)) != null;
    }

    private List<ItemGroup> getItemGroupListFromOrder(String orderId) {
        return getOrderById(orderId).getItemGroupList();
    }

    private void updateStockOfItemGroupList(List<CreateItemGroupDTO> createItemGroupDTO) {
        createItemGroupDTO.forEach(this::updateStockOfItemGroup);
    }

    private void updateStockOfItemGroup(CreateItemGroupDTO itemGroup) {
        itemService.reduceStock(itemGroup.getItemId(), itemGroup.getAmountOfItems());
    }

    public GetOrderHistoryDTO getOrderHistoryByCustomerDto(String userId) {
        return OrderDtoMapper.mapOrderListToGetOrderHistoryDto(orderRepository.getOrdersByUser(ValidationUtil.convertStringToUUID(userId)));
    }
}
