package com.example.springsecurityjwt.model.request;

import lombok.Data;

@Data
public class UserRegisterRq {
    private String name;
    private String username;
    private String password;
}
