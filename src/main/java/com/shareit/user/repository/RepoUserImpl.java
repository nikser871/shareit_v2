package com.shareit.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import com.shareit.item.Item;
import com.shareit.user.MapperUser;
import com.shareit.user.User;
import com.shareit.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class RepoUserImpl implements RepositoryUser{
    Map<Long, User> users = new HashMap<>();
    Map<Long, Set<Item>> usedItems = new HashMap<>();

    MapperUser mapperUser;

    @Autowired
    public RepoUserImpl(MapperUser mapperUser) {
        this.mapperUser = mapperUser;
    }

    @Override
    public User addUser(User user, long id) {
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return users.get(id);
    }

    @Override
    public void deleteUserById(long id) {
        users.remove(id);
    }

    @Override
    public User updateUserById(User user, long id) {
        User user1 = users.get(id);
        String email = user.getEmail();
        String name = user.getName();
        String phoneNumber = user.getPhoneNumber();
        if (email != null) user1.setEmail(email);
        if (name != null) user1.setName(name);
        if (phoneNumber != null) user1.setPhoneNumber(phoneNumber);

        return user1;
    }

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public Set<Item> getUsedItems(long userId) {
        return usedItems.get(userId);
    }

    @Override
    public void addUsedItem(long userId, Item item) {
        usedItems.putIfAbsent(userId, new HashSet<>());
        usedItems.get(userId).add(item);
    }
}
