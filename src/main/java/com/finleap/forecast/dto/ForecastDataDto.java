package com.finleap.forecast.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ForecastDataDto {
    private double averageDaily;
    private double averageNightly;
    private double averagePressure;
}
