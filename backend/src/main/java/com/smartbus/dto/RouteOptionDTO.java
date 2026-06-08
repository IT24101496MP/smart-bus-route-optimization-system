package com.smartbus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteOptionDTO {

    private String busCode;

    private String route;

    private Integer distanceKm;

    private String arrivalTime;

    private Integer journeyTimeMinutes;

    private String dropOffTime;

    private Integer currentPassengers;

    private Integer capacity;

    private Double crowdPercentage;

    private Integer delayMinutes;
}