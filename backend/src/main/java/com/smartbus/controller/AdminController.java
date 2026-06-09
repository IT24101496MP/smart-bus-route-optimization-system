package com.smartbus.controller;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.BusDTO;
import com.smartbus.dto.BusDelayDTO;
import com.smartbus.service.BusService;
import com.smartbus.service.DelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final BusService busService;
    private final DelayService delayService;

    @PostMapping("/bus")
    public ResponseEntity<?> addBus(
            @RequestBody BusDTO dto
    ) {

        if (dto.getBusCode() == null ||
                dto.getBusCode().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Bus code is required"
                    ));
        }

        if (dto.getCapacity() <= 0) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Capacity must be greater than 0"
                    ));
        }

        return busService.addBus(dto);
    }

    @PostMapping("/delay")
    public ResponseEntity<?> addDelay(
            @RequestBody BusDelayDTO dto
    ) {

        if (dto.getBusId() == null) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Bus id is required"
                    ));
        }

        if (dto.getDelayMinutes() < 0) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Delay cannot be negative"
                    ));
        }

        return delayService.addDelay(dto);
    }
}