package com.example.multipledatasource.controller;

import com.example.multipledatasource.model.User;
import com.example.multipledatasource.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getAllFromPrimary")
    public List<User> getAllUsersFromPrimary() {
        return userService.getAllUsersFromPrimary();
    }
    @GetMapping("/getAllFromSecondary")
    public List<User> getAllUsersFromSecondary() {
        return userService.getAllUsersFromSecondary();
    }
}
