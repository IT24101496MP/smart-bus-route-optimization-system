package com.smartbus.repository;

import com.smartbus.entity.Bus;
import com.smartbus.entity.BusRouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRouteStopRepository extends JpaRepository<BusRouteStop, Long> {

    List<BusRouteStop> findByBusOrderByStopOrderAsc(Bus bus);
}