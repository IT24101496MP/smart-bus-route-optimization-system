package com.smartbus.service;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.UserLoginDTO;
import com.smartbus.dto.UserRegisterDTO;
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

        if (!PASSWORD_PATTERN.matcher(dto.getPassword()).matches()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Password must be 8-15 characters and contain uppercase, lowercase, number and special character"
                    ));
        }

        User user = User.builder()
                .firstName(dto.getFirstName().trim())
                .lastName(dto.getLastName().trim())
                .email(dto.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDTO(
                        true,
                        "Registration successful"
                ));
    }

    public ResponseEntity<ApiResponseDTO> login(UserLoginDTO dto) {

        User user = userRepository.findByEmail(
                dto.getEmail().trim().toLowerCase()
        ).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDTO(
                            false,
                            "Invalid email"
                    ));
        }

        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        )) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDTO(
                            false,
                            "Incorrect password"
                    ));
        }

        return ResponseEntity.ok(
                new ApiResponseDTO(
                        true,
                        "Login successful"
                )
        );
    }
}