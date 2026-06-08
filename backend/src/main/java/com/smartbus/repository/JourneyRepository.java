package com.smartbus.repository;

import com.smartbus.entity.Journey;
import com.smartbus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JourneyRepository extends JpaRepository<Journey, Long> {

    List<Journey> findByUser(User user);
}