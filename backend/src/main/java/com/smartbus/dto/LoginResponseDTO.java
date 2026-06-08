package com.smartbus.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private boolean success;

    private String message;

    private Long userId;

    private String role;
}