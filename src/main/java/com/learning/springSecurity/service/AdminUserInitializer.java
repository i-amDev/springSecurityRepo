package com.learning.springSecurity.service;

import com.learning.springSecurity.entity.Role;
import com.learning.springSecurity.entity.Users;
import com.learning.springSecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);
                System.out.println("Default admin user created!!");
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                Users user = new Users();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user"));
                user.setRole(Role.USER);

                userRepository.save(user);
                System.out.println("Default user created!!");
            }
        };
    }
}
