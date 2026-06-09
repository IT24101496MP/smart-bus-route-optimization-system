package com.smartbus.controller;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.RouteOptionDTO;
import com.smartbus.dto.RouteSearchDTO;
import com.smartbus.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/search")
    public ResponseEntity<?> searchRoute(
            @RequestBody RouteSearchDTO dto
    ) {

        if (dto.getSourceCity() == null ||
                dto.getSourceCity().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Source city is required"
                    ));
        }

        if (dto.getDestinationCity() == null ||
                dto.getDestinationCity().trim().isEmpty()) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Destination city is required"
                    ));
        }

        if (dto.getSourceCity()
                .equalsIgnoreCase(
                        dto.getDestinationCity())) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Source and destination cannot be same"
                    ));
        }

        List<RouteOptionDTO> routes =
                routeService.findBestRoutes(dto);

        return ResponseEntity.ok(routes);
    }
}