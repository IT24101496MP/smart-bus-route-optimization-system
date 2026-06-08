package com.smartbus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusScheduleDTO {

    private Long busId;

    private String startTime;

    private String endTime;

    private Integer turnaroundMinutes;
}