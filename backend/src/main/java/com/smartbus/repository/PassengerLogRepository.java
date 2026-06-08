package com.smartbus.repository;

import com.smartbus.entity.Bus;
import com.smartbus.entity.PassengerLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerLogRepository extends JpaRepository<PassengerLog, Long> {

    List<PassengerLog> findByBus(Bus bus);
}