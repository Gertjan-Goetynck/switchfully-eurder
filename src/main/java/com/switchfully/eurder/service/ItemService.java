package com.switchfully.eurder.service;

import com.switchfully.eurder.api.dtos.item.CreateItemDTO;
import com.switchfully.eurder.api.dtos.item.GetItemDto;
import com.switchfully.eurder.api.dtos.item.UpdateItemDTO;
import com.switchfully.eurder.api.dtos.mappers.ItemDtoMapper;
import com.switchfully.eurder.domain.item.Item;
import com.switchfully.eurder.domain.item.ItemRepository;
import com.switchfully.eurder.infrastructure.exceptions.ItemNotFoundException;
import com.switchfully.eurder.infrastructure.utils.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public UUID createItem(CreateItemDTO createItemDTO) {
        Item item = ItemDtoMapper.mapCreateItemDtoToItem(createItemDTO);
        itemRepository.addItem(item);
        return item.getId();
    }

    public Item getItemById(String itemId) {
        return itemRepository.getById(ValidationUtil.convertStringToUUID(itemId));
    }

    public List<GetItemDto> getAllItemsDto() {
        return ItemDtoMapper.mapItemListToGetItemDtoList(new ArrayList<>(itemRepository.getAll().values()));
    }

    public List<GetItemDto> getAllItemsDtoSortedByStock(String stockUrgency) {
        List<GetItemDto> getItemDtoList = getAllItemsDto().stream()
                .sorted(Comparator.comparingInt(GetItemDto::getAmountInStock)).collect(Collectors.toList());
        if (ValidationUtil.isNullObject(stockUrgency) || stockUrgency.isBlank()) {
            return getItemDtoList;
        }

        return getItemDtoList.stream()
                .filter(getItemDto -> getItemDto.getStockUrgency().toString().equalsIgnoreCase(stockUrgency))
                .collect(Collectors.toList());
    }

    public void updateItem(UpdateItemDTO updateItemDTO, String itemId) {
        throwExceptionIfItemNotFound(itemId);
        itemRepository.addItem(
                itemRepository.getById(ValidationUtil.convertStringToUUID(itemId))
                        .setName(updateItemDTO.getName())
                        .setDescription(updateItemDTO.getDescription())
                        .setPricePerUnit(updateItemDTO.getPricePerUnit())
                        .setAmountInStock(updateItemDTO.getAmountInStock())
        );
    }

    public void reduceStock(String itemId, int amount) {
        throwExceptionIfItemNotFound(itemId);
        ValidationUtil.throwExceptionIfNegativeNumber(amount, "Amount bought");
        UUID uuid = ValidationUtil.convertStringToUUID(itemId);
        if (itemRepository.getById(uuid).getAmountInStock() <= amount) {
            itemRepository.getById(uuid).setAmountInStock(0);
        } else {
            itemRepository.getById(uuid).setAmountInStock(itemRepository.getById(uuid).getAmountInStock() - amount);
        }
    }

    private void throwExceptionIfItemNotFound(String itemId) {
        if (ValidationUtil.isNullObject(getItemById(itemId))) {
            throw new ItemNotFoundException();
        }
    }
}
