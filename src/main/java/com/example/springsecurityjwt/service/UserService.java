package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.entity.UserEntity;
import com.example.springsecurityjwt.model.request.UserRq;
import com.example.springsecurityjwt.model.response.UserRs;

public interface UserService {
    UserRs signup(UserRq userRq);
}

