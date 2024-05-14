package org.example.maildemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.dto.UserDto;
import org.example.maildemo.entity.User;
import org.example.maildemo.service.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserFacade userFacade;

    @GetMapping
    public List<User> getUsers() {
        return userFacade.getUsers();
    }

    @GetMapping("/token-user")
    public UserDto getUserByToken(){
        return userFacade.getUserByToken();
    }

}
