package com.smartbus.service;

import com.smartbus.entity.BusDelay;
import com.smartbus.repository.BusDelayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DelayService {

    private final BusDelayRepository busDelayRepository;

    public BusDelay saveDelay(BusDelay delay){
        return busDelayRepository.save(delay);
    }

    public List<BusDelay> getAllDelays(){
        return busDelayRepository.findAll();
    }
}