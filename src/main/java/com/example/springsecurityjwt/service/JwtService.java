package com.example.springsecurityjwt.service;

public interface JwtService {
    String extractUsername(String jwt);
}
