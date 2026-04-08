package com.learning.springSecurity.service;

import com.learning.springSecurity.entity.RegisterUserRequest;
import com.learning.springSecurity.entity.UserResponse;
import com.learning.springSecurity.entity.Users;
import com.learning.springSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        // Check if user is already present
        if (userRepository.findByUsername(registerUserRequest.getUsername()).isPresent()) {
            throw new RuntimeException("User Already Exists");
        }

        // Encode password
        Users user = new Users();
        user.setUsername(registerUserRequest.getUsername());
        user.setRole(registerUserRequest.getRole());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));

        // Save user
        Users savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole().name());
    }
}
