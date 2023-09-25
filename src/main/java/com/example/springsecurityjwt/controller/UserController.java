package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.model.request.UserLoginRq;
import com.example.springsecurityjwt.model.request.UserRegisterRq;
import com.example.springsecurityjwt.model.response.UserLoginRs;
import com.example.springsecurityjwt.model.response.UserRegisterRs;
import com.example.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterRs> signup(@RequestBody UserRegisterRq userRq) {
        UserRegisterRs userRs = userService.register(userRq);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRs);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginRs> signup(@RequestBody UserLoginRq userRq) {
        UserLoginRs userRs = userService.login(userRq);
        return ResponseEntity.status(HttpStatus.OK).body(userRs);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body("List of all users");
    }
}

