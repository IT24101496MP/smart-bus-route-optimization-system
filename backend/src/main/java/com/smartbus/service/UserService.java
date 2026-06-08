package com.smartbus.service;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.LoginResponseDTO;
import com.smartbus.dto.UserLoginDTO;
import com.smartbus.dto.UserRegisterDTO;
import com.smartbus.entity.Role;
import com.smartbus.entity.User;
import com.smartbus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$"
            );

    public ResponseEntity<ApiResponseDTO> registerUser(UserRegisterDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponseDTO(false,
                            "Email already exists"));
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(false,
                            "Passwords do not match"));
        }

        if (!PASSWORD_PATTERN.matcher(dto.getPassword()).matches()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Password must contain uppercase, lowercase, number and special character"
                    ));
        }

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail().toLowerCase())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDTO(
                        true,
                        "Registration successful"
                ));
    }

    public ResponseEntity<LoginResponseDTO> login(UserLoginDTO dto) {

        User user = userRepository.findByEmail(
                dto.getEmail().toLowerCase()
        ).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(LoginResponseDTO.builder()
                            .success(false)
                            .message("Invalid email")
                            .build());
        }

        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword())) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(LoginResponseDTO.builder()
                            .success(false)
                            .message("Incorrect password")
                            .build());
        }

        return ResponseEntity.ok(
                LoginResponseDTO.builder()
                        .success(true)
                        .message("Login successful")
                        .userId(user.getId())
                        .role(user.getRole().name())
                        .build()
        );
    }
}