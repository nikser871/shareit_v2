package com.shareit.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shareit.item.repository.RepositoryItem;
import com.shareit.item.service.ItemService;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    RepositoryItem repositoryItem;
    MapperItem mapperItem;
    ItemService itemService;


    @Autowired
    public ItemController(RepositoryItem repositoryItem, MapperItem mapperItem,
                          ItemService itemService) {
        this.repositoryItem = repositoryItem;
        this.mapperItem = mapperItem;
        this.itemService = itemService;
    }

    @PostMapping
    public Item addItem(@RequestBody ItemDto itemDto, @RequestHeader("X-Later-User-Id") long id) {
        return itemService.addItem(mapperItem.toItem(itemDto, id));
    }

    @PatchMapping(value = "/item/{itemId}")
    public Item modifyItem(@RequestBody ItemDto itemDto,
                           @PathVariable long itemId, @RequestHeader("X-Later-User-Id") long id) {
        return itemService.modifyItem(itemDto, itemId, id);
    }

    @GetMapping(value = "/item/{itemId}")
    public ItemDto getItemById(@PathVariable long itemId) {
        return mapperItem.toDto(repositoryItem.getItemById(itemId));
    }

    @GetMapping
    public Collection<ItemDto> getItems(@RequestHeader("X-Later-User-Id") long id) {
        return itemService.getItems(id);
    }

    @GetMapping(value = "/search")
        public Collection<ItemDto> search(@RequestParam String text) {
            return itemService.searchItem(text);
    }

    @GetMapping(value = "/item/used")
    public Set<Item> getUsedItems(@RequestHeader("X-Later-User-Id") long id) {
        return itemService.getUsedItems(id);
    }

    @PostMapping(value = "/item/feedback/{itemId}")
    public String writeFeedback(@RequestBody String text,
                                @PathVariable long itemId, @RequestHeader("X-Later-User-Id") long id) {
        return itemService.writeFeedback(text, itemId, id);
    }

    @GetMapping(value = "/item/feedback/{itemId}")
    public Collection<String> getFeedbacks(@PathVariable long itemId) {
        return repositoryItem.getFeedbacks(itemId);
    }

    @DeleteMapping(value = "/item/{itemId}")
    public String deleteItemById(@PathVariable long itemId, @RequestHeader("X-Later-User-Id") long id) {
        return repositoryItem.deleteItemById(itemId, id);
    }









}
