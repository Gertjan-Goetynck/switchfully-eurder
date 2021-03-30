package com.switchfully.eurder.api.dtos.mappers;

import com.switchfully.eurder.api.dtos.item.CreateItemDTO;
import com.switchfully.eurder.domain.item.Item;

public class ItemDtoMapper {
    public static Item mapCreateItemDtoToItem(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPricePerUnit(), createItemDTO.getAmountInStock());
    }
}
