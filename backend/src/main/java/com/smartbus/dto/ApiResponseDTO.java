package com.smartbus.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDTO {

    private boolean success;
    private String message;
}