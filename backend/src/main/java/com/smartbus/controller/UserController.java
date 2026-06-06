package com.smartbus.controller;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.UserLoginDTO;
import com.smartbus.dto.UserRegisterDTO;
import com.smartbus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody UserRegisterDTO dto
    ) {

        if (dto.getFirstName() == null ||
                dto.getFirstName().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "First name is required"
                    ));
        }

        if (dto.getLastName() == null ||
                dto.getLastName().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Last name is required"
                    ));
        }

        if (dto.getEmail() == null ||
                dto.getEmail().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Email is required"
                    ));
        }

        if (!dto.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@(.+)$"
        )) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Invalid email format"
                    ));
        }

        if (dto.getPassword() == null ||
                dto.getPassword().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Password is required"
                    ));
        }

        if (dto.getConfirmPassword() == null ||
                dto.getConfirmPassword().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Confirm password is required"
                    ));
        }

        if (!dto.getPassword()
                .equals(dto.getConfirmPassword())) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Passwords do not match"
                    ));
        }

        return userService.registerUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody UserLoginDTO dto
    ) {

        if (dto.getEmail() == null ||
                dto.getEmail().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Email is required"
                    ));
        }

        if (dto.getPassword() == null ||
                dto.getPassword().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Password is required"
                    ));
        }

        return userService.login(dto);
    }
}