package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.request.UserLoginRq;
import com.example.springsecurityjwt.model.request.UserRegisterRq;
import com.example.springsecurityjwt.model.response.UserLoginRs;
import com.example.springsecurityjwt.model.response.UserRegisterRs;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegisterRs register(UserRegisterRq userRq);
    UserLoginRs login(UserLoginRq userRq);
}

