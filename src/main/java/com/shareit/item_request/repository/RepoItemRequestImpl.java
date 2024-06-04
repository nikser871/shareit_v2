package com.shareit.item_request.repository;

import com.shareit.item.Item;
import com.shareit.item_request.ItemRequest;
import com.shareit.user.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RepoItemRequestImpl implements RepositoryItemRequest {

    Map<Long, Set<ItemRequest>> requests = new HashMap<>();
    Map<Long, Set<Item>> compilation = new HashMap<>();
    RepositoryUser repositoryUser;

    @Autowired
    public RepoItemRequestImpl(RepositoryUser repositoryUser) {
            this.repositoryUser = repositoryUser;
    }

    @Override
    public ItemRequest addItemRequest(ItemRequest itemRequest, long userId) {
        itemRequest.setUser(repositoryUser.getUserById(userId));
        requests.putIfAbsent(userId, new HashSet<>());
        requests.get(userId).add(itemRequest);

        return itemRequest;
    }

    @Override
    public String deleteCompilation(long userId) {
        compilation.put(userId, null);
        return "GOOD DELETION OF A COMPILATION!!!";
    }

    @Override
    public Collection<Item> getCompilation(long userId) {
        return compilation.get(userId);
    }

    @Override
    public void addCompilation(Item item, long userId) {
        compilation.putIfAbsent(userId, new HashSet<>());
        compilation.get(userId).add(item);
    }

    @Override
    public Collection<ItemRequest> getAllRequests() {
        return requests.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


}
