package com.smartbus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "passenger_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(nullable = false)
    private Integer boardedCount;

    @Column(nullable = false)
    private Integer exitedCount;

    private LocalDateTime logTime;
}