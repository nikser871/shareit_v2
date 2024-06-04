package com.shareit.user;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private long id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;

}
