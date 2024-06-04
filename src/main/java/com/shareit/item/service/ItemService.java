package com.shareit.item.service;

import com.shareit.item.Item;
import com.shareit.item.ItemDto;

import java.util.Collection;
import java.util.Set;

public interface ItemService {

    Collection<ItemDto> searchItem(String text);
    String writeFeedback(String text, long itemId, long userId);

    Set<Item> getUsedItems(long userId);
    Item addItem(Item item);
    Item modifyItem(ItemDto itemDto, long itemId, long userId);
    Collection<ItemDto> getItems(long id);
}
