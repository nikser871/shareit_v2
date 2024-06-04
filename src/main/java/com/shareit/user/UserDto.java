package com.shareit.user;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private long id;
    private String name;
    private String phoneNumber;
    private String email;
}
