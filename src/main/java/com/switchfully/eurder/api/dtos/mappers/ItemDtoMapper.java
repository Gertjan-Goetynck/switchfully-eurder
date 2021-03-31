package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.item.CreateItemDTO;
import com.switchfully.eurder.api.dtos.item.GetItemDto;
import com.switchfully.eurder.domain.item.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDtoMapper {
    public static Item mapCreateItemDtoToItem(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPricePerUnit(), createItemDTO.getAmountInStock());
    }

    public static GetItemDto mapItemToGetItemDto(Item item) {
        return new GetItemDto(item.getId(), item.getName(), item.getDescription(), item.getPricePerUnit(), item.getAmountInStock());
    }

    public static List<GetItemDto> mapItemListToGetItemDtoList(List<Item> itemList) {
        return itemList.stream()
                .map(ItemDtoMapper::mapItemToGetItemDto)
                .collect(Collectors.toList());
    }
}
