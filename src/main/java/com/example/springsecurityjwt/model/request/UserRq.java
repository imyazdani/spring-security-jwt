package com.example.springsecurityjwt.model.request;

import lombok.Data;

@Data
public class UserRq {
    private String name;
    private String username;
    private String password;
}
