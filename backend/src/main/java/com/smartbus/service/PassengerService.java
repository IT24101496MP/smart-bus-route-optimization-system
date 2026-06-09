package com.smartbus.service;

import com.smartbus.entity.Bus;
import com.smartbus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final BusRepository busRepository;

    public void boardPassenger(Long busId){

        Bus bus = busRepository.findById(busId).orElse(null);

        if(bus != null){

            bus.setCurrentPassengerCount(
                    bus.getCurrentPassengerCount() + 1
            );

            busRepository.save(bus);
        }
    }

    public void exitPassenger(Long busId){

        Bus bus = busRepository.findById(busId).orElse(null);

        if(bus != null &&
                bus.getCurrentPassengerCount() > 0){

            bus.setCurrentPassengerCount(
                    bus.getCurrentPassengerCount() - 1
            );

            busRepository.save(bus);
        }
    }

    public double getCrowdPercentage(Long busId){

        Bus bus = busRepository.findById(busId).orElse(null);

        if(bus == null){
            return 0;
        }

        return (bus.getCurrentPassengerCount() * 100.0)
                / bus.getCapacity();
    }
}