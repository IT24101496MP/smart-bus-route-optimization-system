package com.smartbus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bus_route_stops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusRouteStop {

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
    private Integer stopOrder;

    @Column(nullable = false)
    private Integer arrivalOffsetMinutes;
}