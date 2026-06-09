package com.smartbus.service;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.PassengerLogDTO;
import com.smartbus.entity.Bus;
import com.smartbus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final BusRepository busRepository;

    public ResponseEntity<?> boardBus(
            PassengerLogDTO dto) {

        Bus bus =
                busRepository.findById(
                        dto.getBusId()
                ).orElse(null);

        if (bus == null) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Bus not found"
                    ));
        }

        bus.setCurrentPassengerCount(
                bus.getCurrentPassengerCount() + 1
        );

        busRepository.save(bus);

        return ResponseEntity.ok(
                new ApiResponseDTO(
                        true,
                        "Passenger boarded successfully"
                )
        );
    }

    public ResponseEntity<?> exitBus(
            PassengerLogDTO dto) {

        Bus bus =
                busRepository.findById(
                        dto.getBusId()
                ).orElse(null);

        if (bus == null) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(
                            false,
                            "Bus not found"
                    ));
        }

        if (bus.getCurrentPassengerCount() > 0) {

            bus.setCurrentPassengerCount(
                    bus.getCurrentPassengerCount() - 1
            );

            busRepository.save(bus);
        }

        return ResponseEntity.ok(
                new ApiResponseDTO(
                        true,
                        "Passenger exited successfully"
                )
        );
    }

    public double getCrowdPercentage(Long busId) {

        Bus bus =
                busRepository.findById(busId)
                        .orElse(null);

        if (bus == null) {
            return 0;
        }

        return (bus.getCurrentPassengerCount() * 100.0)
                / bus.getCapacity();
    }
}