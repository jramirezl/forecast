package com.finleap.forecast.service;

import com.finleap.forecast.dto.ForecastDataDto;

public interface ForecastService {
    ForecastDataDto getAverageFromCity(String city);
}
