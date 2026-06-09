package com.smartbus.service;

import com.smartbus.dto.ApiResponseDTO;
import com.smartbus.dto.BusDTO;
import com.smartbus.entity.Bus;
import com.smartbus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public ResponseEntity<?> addBus(BusDTO dto) {

        Bus bus = Bus.builder()
                .busCode(dto.getBusCode())
                .capacity(dto.getCapacity())
                .currentPassengerCount(0)
                .active(true)
                .build();

        busRepository.save(bus);

        return ResponseEntity.ok(
                new ApiResponseDTO(
                        true,
                        "Bus added successfully"
                )
        );
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBus(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
}