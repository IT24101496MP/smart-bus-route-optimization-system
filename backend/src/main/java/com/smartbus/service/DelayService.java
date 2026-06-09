package com.smartbus.service;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.BusDelayDTO;
import com.smartbus.entity.Bus;
import com.smartbus.entity.BusDelay;
import com.smartbus.repository.BusDelayRepository;
import com.smartbus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DelayService {

    private final BusDelayRepository busDelayRepository;
    private final BusRepository busRepository;

    public ResponseEntity<?> addDelay(
            BusDelayDTO dto) {

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

        BusDelay delay = BusDelay.builder()
                .bus(bus)
                .delayMinutes(dto.getDelayMinutes())
                .reason(dto.getReason())
                .createdAt(LocalDateTime.now())
                .build();

        busDelayRepository.save(delay);

        return ResponseEntity.ok(
                new ApiResponseDTO(
                        true,
                        "Delay recorded successfully"
                )
        );
    }

    public BusDelay saveDelay(BusDelay delay) {
        return busDelayRepository.save(delay);
    }

    public List<BusDelay> getAllDelays() {
        return busDelayRepository.findAll();
    }
}