package com.smartbus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusRouteStopDTO {

    private Long busId;

    private Long cityId;

    private Integer stopOrder;

    private Integer arrivalOffsetMinutes;
}