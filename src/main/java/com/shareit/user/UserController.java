package com.shareit.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shareit.user.repository.RepositoryUser;

import java.util.Collection;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    RepositoryUser repositoryUser;
    MapperUser mapperUser;

    @Autowired
    public UserController(RepositoryUser repositoryUser, MapperUser mapperUser) {
        this.repositoryUser = repositoryUser;
        this.mapperUser = mapperUser;
    }

    @GetMapping(value = "/user")
    public UserDto getUserById(@RequestHeader("X-Later-User-Id") long id) {
        return mapperUser.toDto(repositoryUser.getUserById(id));
    }

    @DeleteMapping(value = "/user")
    public String deleteUserById(@RequestHeader("X-Later-User-Id") long id){
        repositoryUser.deleteUserById(id);
        return "Remove has being done!";
    }

    @PutMapping(value = "/user")
    public UserDto updateUserById(@RequestBody User user, @RequestHeader("X-Later-User-Id") long id) {
        return mapperUser.toDto(repositoryUser.updateUserById(user, id));
    }

    @PostMapping(value = "/user")
    public UserDto addUser(@RequestBody User user,
                           @RequestHeader("X-Later-User-Id") long id) {
        return mapperUser.toDto(repositoryUser.addUser(user, id));
    }




    @GetMapping
    public Collection<UserDto> getUsers() {
        return repositoryUser.getUsers()
                .stream()
                .map(x -> mapperUser.toDto(x))
                .toList();
    }

}
