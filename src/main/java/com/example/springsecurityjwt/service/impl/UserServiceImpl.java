package com.example.springsecurityjwt.service.impl;

import com.example.springsecurityjwt.exception.UserDuplicateException;
import com.example.springsecurityjwt.exception.UserNotFoundException;
import com.example.springsecurityjwt.model.entity.UserEntity;
import com.example.springsecurityjwt.model.request.UserRq;
import com.example.springsecurityjwt.model.response.UserRs;
import com.example.springsecurityjwt.repository.UserRepository;
import com.example.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserRs signup(UserRq userRq) {
        Optional<UserEntity> userFound = userRepository.findByUsername(userRq.getUsername());
        if (userFound.isEmpty()){
            UserEntity userEntity = modelMapper.map(userRq, UserEntity.class);
            UserEntity userSaved = userRepository.save(userEntity);
            return modelMapper.map(userSaved, UserRs.class);
        } else {
            throw new UserDuplicateException(userFound.get().getUsername());
        }
    }

    private UserEntity getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}