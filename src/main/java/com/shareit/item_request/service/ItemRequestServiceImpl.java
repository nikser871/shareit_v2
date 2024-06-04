package com.shareit.item_request.service;


import com.shareit.item.Item;
import com.shareit.item.ItemDto;
import com.shareit.item_request.repository.RepositoryItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemRequestServiceImpl implements ItemRequestService{

    RepositoryItemRequest repositoryItemRequest;

    @Autowired
    public ItemRequestServiceImpl(RepositoryItemRequest repositoryItemRequest) {
        this.repositoryItemRequest = repositoryItemRequest;
    }

    @Override
    public Collection<ItemDto> getCompilation(long userId) {
        Collection<Item> compilations = repositoryItemRequest.getCompilation(userId);

        return compilations.stream()
                .map(x -> new ItemDto(x.getId(), x.getName(), x.getDescription(), x.isAvailable(),
                x.getCountOfBookings()))
                .toList();
    }
}
