package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.model.entity.UserEntity;
import com.example.springsecurityjwt.model.enums.UserRoleEnum;
import com.example.springsecurityjwt.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdminConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void insertAdminUser(){
        Optional<UserEntity> adminStored = userRepository.findByUsername("admin");
        if (adminStored.isEmpty()){
            UserEntity adminEntity = new UserEntity();
            adminEntity.setName("admin");
            adminEntity.setUsername("admin");
            adminEntity.setPassword(passwordEncoder.encode("admin"));
            adminEntity.setRole(UserRoleEnum.ADMIN);
            userRepository.save(adminEntity);
            log.info("Admin user saved successfully.");
        }else {
            log.warn("Admin user already exists.");
        }
    }
}
