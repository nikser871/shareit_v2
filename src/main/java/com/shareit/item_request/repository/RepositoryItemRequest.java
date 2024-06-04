package com.shareit.item_request.repository;

import com.shareit.item.Item;
import com.shareit.item_request.ItemRequest;

import java.util.Collection;
import java.util.Set;

public interface RepositoryItemRequest {
    ItemRequest addItemRequest(ItemRequest itemRequest, long userId);
    String deleteCompilation(long userId);
    Collection<Item> getCompilation(long userId);

    void addCompilation(Item item, long userId);

    Collection<ItemRequest> getAllRequests();


}
