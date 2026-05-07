package com.example.taskManagementBackendMain.controller;

import com.example.taskManagementBackendMain.entity.User;
import com.example.taskManagementBackendMain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String userLogin(@RequestBody User user){
        return userService.logIn(user);
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody User user){
        return userService.signUp(user);
    }
}