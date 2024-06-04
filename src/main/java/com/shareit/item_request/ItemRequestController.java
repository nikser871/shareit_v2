package com.shareit.item_request;


import com.shareit.item.Item;
import com.shareit.item.ItemDto;
import com.shareit.item_request.repository.RepositoryItemRequest;
import com.shareit.item_request.service.ItemRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/item_request")
public class ItemRequestController {
    RepositoryItemRequest repositoryItemRequest;
    ItemRequestService itemRequestService;

    @Autowired
    public ItemRequestController(RepositoryItemRequest repositoryItemRequest, ItemRequestService itemRequestService) {
        this.repositoryItemRequest = repositoryItemRequest;
        this.itemRequestService = itemRequestService;
    }

    @PostMapping
    public ItemRequest addItemRequest(@RequestBody ItemRequest itemRequest, @RequestHeader("X-Later-User-Id") long id) {
        return repositoryItemRequest.addItemRequest(itemRequest, id);
    }

    @GetMapping
    public Collection<ItemDto> getCompilation(@RequestHeader("X-Later-User-Id") long id) {
        return itemRequestService.getCompilation(id);
    }

    @DeleteMapping
    public String deleteCompilation(@RequestHeader("X-Later-User-Id") long id) {
        return repositoryItemRequest.deleteCompilation(id);
    }



}
