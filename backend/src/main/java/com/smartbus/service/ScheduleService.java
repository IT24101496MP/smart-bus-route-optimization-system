package com.smartbus.service;

import com.smartbus.entity.BusSchedule;
import com.smartbus.repository.BusScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final BusScheduleRepository busScheduleRepository;

    public BusSchedule save(BusSchedule schedule){
        return busScheduleRepository.save(schedule);
    }

    public List<BusSchedule> getAllSchedules(){
        return busScheduleRepository.findAll();
    }
}