package com.finleap.forecast.service;


import com.finleap.forecast.dto.ResponseDto;

public interface WeatherRestService {

    ResponseDto getWeatherByCity(String city);
}
