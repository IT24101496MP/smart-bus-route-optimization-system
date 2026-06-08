package com.smartbus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadDTO {

    private Long sourceCityId;

    private Long destinationCityId;

    private Integer distanceKm;

    private Integer travelTimeMinutes;
}