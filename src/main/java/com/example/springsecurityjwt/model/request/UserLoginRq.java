package com.example.springsecurityjwt.model.request;

import lombok.Data;

@Data
public class UserLoginRq {
    private String username;
    private String password;
}
