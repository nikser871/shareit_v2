package com.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shareit.item.repository.RepositoryItem;
import com.shareit.user.MapperUser;
import com.shareit.user.repository.RepositoryUser;

@Component
public class MapperItem {

    static long id = 1;

    RepositoryUser repositoryUser;
    MapperUser mapperUser;


    @Autowired
    public MapperItem(RepositoryUser repositoryUser, MapperUser mapperUser) {
        this.repositoryUser = repositoryUser;
        this.mapperUser = mapperUser;
    }

    public ItemDto toDto(Item item) {
        return new ItemDto(item.getId(),item.getName(),
                item.getDescription(),
                item.isAvailable(), item.getCountOfBookings());
    }

    public Item toItem(ItemDto itemDto, long ownerId) {
        return new Item(id++, repositoryUser.getUserById(ownerId),
                itemDto.getName(), itemDto.getDescription(),
                true, null, 0);
    }

}
