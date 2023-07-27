package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.request.UserRq;
import com.example.springsecurityjwt.model.response.UserRs;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRs signup(UserRq userRq);
}

