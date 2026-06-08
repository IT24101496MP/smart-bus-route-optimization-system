package com.smartbus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bus_delays")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusDelay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Column(nullable = false)
    private Integer delayMinutes;

    private String reason;

    private LocalDateTime createdAt;
}