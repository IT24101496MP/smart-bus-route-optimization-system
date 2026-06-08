package com.smartbus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerLogDTO {

    private Long busId;

    private Long cityId;

    private Integer boardedCount;

    private Integer exitedCount;
}