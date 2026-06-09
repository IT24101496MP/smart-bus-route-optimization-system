package com.smartbus.controller;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.PassengerLogDTO;
import com.smartbus.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/board")
    public ResponseEntity<?> boardBus(
            @RequestBody PassengerLogDTO dto
    ) {

        if (dto.getBusId() == null) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Bus id is required"
                    ));
        }

        return passengerService.boardBus(dto);
    }

    @PostMapping("/exit")
    public ResponseEntity<?> exitBus(
            @RequestBody PassengerLogDTO dto
    ) {

        if (dto.getBusId() == null) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Bus id is required"
                    ));
        }

        return passengerService.exitBus(dto);
    }
}