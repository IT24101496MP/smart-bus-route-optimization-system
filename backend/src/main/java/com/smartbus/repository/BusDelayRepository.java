package com.smartbus.repository;

import com.smartbus.entity.Bus;
import com.smartbus.entity.BusDelay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusDelayRepository extends JpaRepository<BusDelay, Long> {

    List<BusDelay> findByBus(Bus bus);
}