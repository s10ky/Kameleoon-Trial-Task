package com.example.github.controller;

import com.example.github.model.User;
import com.example.github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    ResponseEntity<User> createUser(@RequestBody User user) {
        user.setCreateDate(Date.valueOf(LocalDate.now()));
        return ResponseEntity.ok().body(userService.createUser(user));
    }
}
