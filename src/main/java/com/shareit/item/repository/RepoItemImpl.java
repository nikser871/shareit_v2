package com.shareit.item.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shareit.item.Item;
import com.shareit.item.ItemDto;
import com.shareit.user.repository.RepositoryUser;

import java.util.*;

@Component
public class RepoItemImpl implements RepositoryItem{

    Map<Long, Item> items = new HashMap<>();
    Map<Long, Set<Item>> itemsOwners = new HashMap<>();
    Map<Long, Set<String>> feedback = new HashMap<>();

    static int ID = 1;



    @Override
    public Item addItem(Item item) {
        item.setId(ID);
        itemsOwners.putIfAbsent(item.getOwner().getId(), new HashSet<>());
        itemsOwners.get(item.getOwner().getId()).add(item);
        items.put(item.getId(), item);
        ID++;
        return item;
    }

    @Override
    public Item modifyItem(ItemDto itemDto, long itemId) {
        String name = itemDto.getName();
        String description = itemDto.getDescription();

        boolean availability = itemDto.isAvailable();


        if (name != null) items.get(itemId).setName(name);
        if (description != null) items.get(itemId).setDescription(description);
        items.get(itemId).setAvailable(availability);

        return items.get(itemId);

    }

    @Override
    public Item getItemById(long itemId) {
        return items.get(itemId);
    }

    @Override
    public Collection<Item> getItems(long id) {
        return itemsOwners.get(id);
    }

    @Override
    public Collection<Item> getAllItems() {
        Collection<Item> items = new HashSet<>();
        for (Set<Item> a: itemsOwners.values()
             ) {
            items.addAll(a);
        }
        return items;

    }

    @Override
    public String writeFeedback(String text, long itemId) {
        feedback.putIfAbsent(itemId, new HashSet<>());
        feedback.get(itemId).add(text);
        return "GOOD!!! Thank you!";
    }

    @Override
    public Collection<String> getFeedbacks(long itemId) {
        return feedback.get(itemId);
    }

    @Override
    public String deleteItemById(long itemId, long userId) {
        itemsOwners.get(userId).remove(items.get(itemId));
        items.remove(itemId);


        return "GOOD!!!";
    }


}
