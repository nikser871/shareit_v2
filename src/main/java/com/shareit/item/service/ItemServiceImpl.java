package com.shareit.item.service;

import com.shareit.exception.AppException;
import com.shareit.item_request.ItemRequest;
import com.shareit.item_request.repository.RepositoryItemRequest;
import com.shareit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shareit.item.Item;
import com.shareit.item.ItemDto;
import com.shareit.item.MapperItem;
import com.shareit.item.repository.RepositoryItem;
import com.shareit.user.repository.RepositoryUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService{

    RepositoryItem repositoryItem;
    MapperItem mapperItem;
    RepositoryUser repositoryUser;
    RepositoryItemRequest repositoryItemRequest;

    @Autowired
    public ItemServiceImpl(RepositoryItem repositoryItem, MapperItem mapperItem,
                           RepositoryUser repositoryUser, RepositoryItemRequest repositoryItemRequest) {
        this.repositoryItem = repositoryItem;
        this.mapperItem = mapperItem;
        this.repositoryUser = repositoryUser;
        this.repositoryItemRequest = repositoryItemRequest;
    }


    @Override
    public Collection<ItemDto> searchItem(String text) {

        Collection<Item> items = repositoryItem.getAllItems();
        Collection<ItemDto> results = new HashSet<>();
        text = text.toLowerCase();

        for (Item item: items
             ) {
            if ((item.getName().toLowerCase().contains(text) ||
                    item.getDescription().toLowerCase().contains(text))
                    && item.isAvailable()) {
                results.add(mapperItem.toDto(item));
            }
        }

        return results;
    }

    @Override
    public String writeFeedback(String text, long itemId, long userId) {
        long count = repositoryUser.getUsedItems(userId)
                .stream()
                .filter(x -> x.getId() == itemId)
                .count();
        if (count == 0) throw new AppException("You haven't got this item!!!");
        return repositoryItem.writeFeedback(text, itemId);
    }

    @Override
    public Set<Item> getUsedItems(long userId) {
        return repositoryUser.getUsedItems(userId);
    }

    @Override
    public Item addItem(Item item) {

        Collection<ItemRequest> requests = repositoryItemRequest.getAllRequests();

        for (ItemRequest request: requests
             ) {
            if (hasRequest(item, request))
                repositoryItemRequest.addCompilation(item, request.getUser().getId());
        }

        return repositoryItem.addItem(item);
    }

    @Override
    public Item modifyItem(ItemDto itemDto, long itemId, long userId) {
        if (isExistSuchItem(userId, itemId)) return repositoryItem.modifyItem(itemDto, itemId);
        throw new AppException("You haven't got this subject!!!");
    }

    @Override
    public Collection<ItemDto> getItems(long id) {
        Collection<Item> items = repositoryItem.getItems(id);

        return items.stream()
                .map(x -> new ItemDto(x.getId(), x.getName(), x.getDescription(),
                        x.isAvailable(), x.getCountOfBookings()))
                .toList();
    }


    public boolean isExistSuchItem(long userId, long itemId) {
        User user = repositoryUser.getUserById(userId);
        return repositoryItem.getItemById(itemId).getOwner().equals(user);
    }
    private boolean hasRequest(Item item, ItemRequest itemRequest) {
        return item.getDescription().toLowerCase().contains(itemRequest.getName()) ||
                item.getName().toLowerCase().contains(itemRequest.getName());
    }



}
