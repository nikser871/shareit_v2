package com.shareit.item.repository;

import com.shareit.item.Item;
import com.shareit.item.ItemDto;

import java.util.Collection;
import java.util.Set;

public interface RepositoryItem {

    Item addItem(Item item);
    Item modifyItem(ItemDto itemDto, long itemId);

    Item getItemById(long itemId);
    Collection<Item> getItems(long id);

    Collection<Item> getAllItems();

    String writeFeedback(String text, long itemId);

    Collection<String> getFeedbacks(long itemId);
    String deleteItemById(long itemId, long userId);

}
