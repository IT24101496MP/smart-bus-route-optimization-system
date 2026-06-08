package com.smartbus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_city_id")
    private City sourceCity;

    @ManyToOne
    @JoinColumn(name = "destination_city_id")
    private City destinationCity;

    @Column(nullable = false)
    private Integer distanceKm;

    @Column(nullable = false)
    private Integer travelTimeMinutes;
}