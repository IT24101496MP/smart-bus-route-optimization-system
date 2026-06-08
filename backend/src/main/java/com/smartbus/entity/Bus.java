package com.smartbus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busCode;

    @Column(nullable = false)
    private Integer capacity;

    @Builder.Default
    private Integer currentPassengerCount = 0;

    @Builder.Default
    private Boolean active = true;
}