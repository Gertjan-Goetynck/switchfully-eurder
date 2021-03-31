package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.order.CreateItemGroupDTO;
import com.switchfully.eurder.api.dtos.order.GetItemGroupDTO;
import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.order.itemgroup.ItemGroup;
import com.switchfully.eurder.service.ItemService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemGroupDtoMapper {
    public static ItemGroup mapCreateItemGroupDtoToItemGroup(CreateItemGroupDTO createItemGroupDTO, ItemService itemService) {
        return new ItemGroup(itemService.getItemById(createItemGroupDTO.getItemId()), createItemGroupDTO.getAmountOfItems());
    }

    public static List<ItemGroup> mapCreateItemGroupDtoListToItemGroupList(List<CreateItemGroupDTO> createItemGroupDtoList, ItemService itemService) {
        return createItemGroupDtoList.stream()
                .map(createItemGroupDTO -> mapCreateItemGroupDtoToItemGroup(createItemGroupDTO, itemService))
                .collect(Collectors.toList());
    }

    public static List<GetItemGroupDTO> mapItemGroupListToGetItemGroupDtoList(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(ItemGroupDtoMapper::mapItemToGetItemGroupDto)
                .collect(Collectors.toList());
    }

    public static GetItemGroupDTO mapItemToGetItemGroupDto(ItemGroup itemGroup) {
        return new GetItemGroupDTO(itemGroup.getPurchasedItem().getName(), itemGroup.getAmountOfItems(), itemGroup.calculateItemGroupPrice());
    }
}
