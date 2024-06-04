package com.shareit.user.repository;

import com.shareit.item.Item;
import com.shareit.user.User;
import com.shareit.user.UserDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public interface RepositoryUser {

    User addUser(User user, long id);
    User getUserById(long id);
    void deleteUserById(long id);
    User updateUserById(User user, long id);
    Collection<User> getUsers();
    Set<Item> getUsedItems(long userId);
    void addUsedItem(long userId, Item item);
}
