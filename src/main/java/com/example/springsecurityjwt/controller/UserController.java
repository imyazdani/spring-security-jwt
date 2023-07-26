package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.model.request.UserRq;
import com.example.springsecurityjwt.model.response.UserRs;
import com.example.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserRs> signup(@RequestBody UserRq userRq) {
        UserRs userRs = userService.signup(userRq);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRs);
    }
}

