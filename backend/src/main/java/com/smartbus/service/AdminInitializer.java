package com.smartbus.service;

import com.smartbus.entity.Role;
import com.smartbus.entity.User;
import com.smartbus.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin() {

        String adminEmail = "admin@gmail.com";

        if (!userRepository.existsByEmail(adminEmail)) {

            User admin = User.builder()
                    .firstName("System")
                    .lastName("Admin")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("Admin@1234"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
        }
    }
}