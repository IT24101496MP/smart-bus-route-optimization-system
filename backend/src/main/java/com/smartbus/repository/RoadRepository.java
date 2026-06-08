package com.smartbus.repository;

import com.smartbus.entity.City;
import com.smartbus.entity.Road;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadRepository extends JpaRepository<Road, Long> {

    List<Road> findBySourceCity(City sourceCity);

    List<Road> findByDestinationCity(City destinationCity);
}