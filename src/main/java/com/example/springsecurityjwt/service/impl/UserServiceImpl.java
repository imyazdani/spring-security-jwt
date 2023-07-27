package com.example.springsecurityjwt.service.impl;

import com.example.springsecurityjwt.exception.UserDuplicateException;
import com.example.springsecurityjwt.exception.UserNotFoundException;
import com.example.springsecurityjwt.model.entity.UserEntity;
import com.example.springsecurityjwt.model.enums.UserRoleEnum;
import com.example.springsecurityjwt.model.request.UserLoginRq;
import com.example.springsecurityjwt.model.request.UserRegisterRq;
import com.example.springsecurityjwt.model.response.UserLoginRs;
import com.example.springsecurityjwt.model.response.UserRegisterRs;
import com.example.springsecurityjwt.repository.UserRepository;
import com.example.springsecurityjwt.service.JwtService;
import com.example.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserRegisterRs register(UserRegisterRq userRq) {
        Optional<UserEntity> userFound = userRepository.findByUsername(userRq.getUsername());
        if (userFound.isEmpty()){
            UserEntity userEntity = modelMapper.map(userRq, UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(userRq.getPassword()));
            userEntity.setRole(UserRoleEnum.USER);

            UserEntity userSaved = userRepository.save(userEntity);
            return modelMapper.map(userSaved, UserRegisterRs.class);
        } else {
            throw new UserDuplicateException(userFound.get().getUsername());
        }
    }

    @Override
    public UserLoginRs login(UserLoginRq userRq) {
        UserDetails userDetails = getUser(userRq.getUsername());

        String jwtToken = jwtService.generateToken(userDetails);
        UserLoginRs userRs = new UserLoginRs();
        userRs.setToken(jwtToken);
        return userRs;
    }

    private UserDetails getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return getUser(username);
    }
}