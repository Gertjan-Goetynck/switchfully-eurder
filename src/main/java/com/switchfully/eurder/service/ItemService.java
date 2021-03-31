package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.item.CreateItemDTO;
import com.switchfully.eurder.api.dtos.item.GetItemDto;
import com.switchfully.eurder.api.dtos.mappers.ItemDtoMapper;
import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.item.ItemRepository;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void createItem(CreateItemDTO createItemDTO) {
        itemRepository.addItem(ItemDtoMapper.mapCreateItemDtoToItem(createItemDTO));
    }

    public Item getItemById(String itemId) {
        return itemRepository.getById(ValidationUtil.convertStringToUUID(itemId));
    }

    public List<GetItemDto> getAllItems() {
        return ItemDtoMapper.mapItemListToGetItemDtoList(new ArrayList<>(itemRepository.getAll().values()));
    }
}
