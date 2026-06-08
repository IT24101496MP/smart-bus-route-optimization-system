package com.smartbus.repository;

import com.smartbus.entity.Bus;
import com.smartbus.entity.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {

    Optional<BusSchedule> findByBus(Bus bus);
}