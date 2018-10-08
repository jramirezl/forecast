package com.finleap.forecast.service;

import com.finleap.forecast.config.OpenWeathermapApiProperties;
import com.finleap.forecast.dto.ResponseDto;
import com.finleap.forecast.util.ClientBuilderProvider;
import com.finleap.forecast.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Qualifier;
import javax.ws.rs.client.Client;

@Service
public class WeatherRestServiceImpl implements WeatherRestService{

    private final OpenWeathermapApiProperties properties;
    ClientBuilderProvider clientBuilderProvider;

    @Autowired
    public WeatherRestServiceImpl(OpenWeathermapApiProperties properties, ClientBuilderProvider clientBuilderProvider) {
        this.properties = properties;
        this.clientBuilderProvider = clientBuilderProvider;
    }

    @Override
    public ResponseDto getWeatherByCity(String city) {
        return clientBuilderProvider.getClient()
                .target(properties.getBaseUrl())
                .queryParam(Constants.ID, city)
                .queryParam(Constants.APPID, properties.getKey())
                .request()
                .get()
                .readEntity(ResponseDto.class);
    }
}
