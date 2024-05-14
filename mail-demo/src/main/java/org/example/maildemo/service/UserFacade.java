package org.example.maildemo.service;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.RegisterRequest;
import org.example.maildemo.dto.UserDto;
import org.example.maildemo.entity.User;
import org.example.maildemo.model.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public ResponseEntity<ResponseMessage> register(RegisterRequest registerInformations){
        registerInformations.setPassword(passwordEncoder.encode(registerInformations.getPassword()));
        return userService.register(registerInformations);
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public UserDto getUserByToken() {
        return userService.getUserByToken();
    }
}
