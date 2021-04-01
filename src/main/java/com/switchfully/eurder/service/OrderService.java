package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.mappers.ItemGroupDtoMapper;
import com.switchfully.eurder.api.dtos.mappers.OrderDtoMapper;
import com.switchfully.eurder.api.dtos.mappers.PriceDtoMapper;
import com.switchfully.eurder.api.dtos.order.CreateOrderDTO;
import com.switchfully.eurder.api.dtos.order.GetOrderHistoryDTO;
import com.switchfully.eurder.api.dtos.order.GetItemGroupShippingDTO;
import com.switchfully.eurder.api.dtos.price.GetPriceDTO;
import com.switchfully.eurder.domain.order.Order;
import com.switchfully.eurder.domain.order.OrderRepository;
import com.switchfully.eurder.domain.order.itemgroup.ItemGroup;
import com.switchfully.eurder.infrastructure.exceptions.ItemNotFoundException;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public GetPriceDTO redoOrder(String orderId) {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(ItemGroupDtoMapper.mapItemGroupListToCreateItemGroupDtoList(getItemGroupListFromOrder(orderId)));
        return placeOrder(createOrderDTO, getOrderById(orderId).getCustomer().getId().toString());
    }

    private Order getOrderById(String orderId) {
        return orderRepository.getOrderById(ValidationUtil.convertStringToUUID(orderId));
    }

    private List<ItemGroup> getItemGroupListFromOrder(String orderId) {
        return getOrderById(orderId).getItemGroupList();
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

    public GetOrderHistoryDTO getOrderHistoryByCustomerDto(String userId) {
        return OrderDtoMapper.mapOrderListToGetOrderHistoryDto(orderRepository.getOrdersByUser(ValidationUtil.convertStringToUUID(userId)));
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
