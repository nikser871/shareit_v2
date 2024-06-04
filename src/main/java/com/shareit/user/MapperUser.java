package com.shareit.user;

import org.springframework.stereotype.Component;
import com.shareit.item.Item;
import com.shareit.item.ItemDto;

@Component
public class MapperUser {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getPhoneNumber(), user.getEmail());
    }

    public User toUser(UserDto userDto, String password) {
        return new User(userDto.getId(), password, userDto.getName(),
                userDto.getPhoneNumber(), userDto.getEmail());
    }

}
