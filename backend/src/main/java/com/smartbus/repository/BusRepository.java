package com.smartbus.repository;

import com.smartbus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {

    Optional<Bus> findByBusCode(String busCode);

    boolean existsByBusCode(String busCode);
}