package com.smartbus.service;

import com.smartbus.entity.Bus;
import com.smartbus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public Bus saveBus(Bus bus){
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses(){
        return busRepository.findAll();
    }

    public Bus getBus(Long id){
        return busRepository.findById(id).orElse(null);
    }

    public void deleteBus(Long id){
        busRepository.deleteById(id);
    }
}