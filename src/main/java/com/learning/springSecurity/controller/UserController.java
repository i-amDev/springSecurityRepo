package com.learning.springSecurity.controller;

import com.learning.springSecurity.entity.RegisterUserRequest;
import com.learning.springSecurity.entity.Role;
import com.learning.springSecurity.entity.UserResponse;
import com.learning.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        registerUserRequest.setRole(Role.USER);
        return ResponseEntity.ok(userService.registerUser(registerUserRequest));
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> registerByAdmin(@RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(userService.registerUser(registerUserRequest));
    }
}
