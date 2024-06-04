package com.shareit.item_request.service;

import com.shareit.item.ItemDto;

import java.util.Collection;

public interface ItemRequestService {
    Collection<ItemDto> getCompilation(long userId);
}
