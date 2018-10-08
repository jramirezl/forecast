package com.finleap.forecast.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.forecast.dto.ForecastDataDto;
import com.finleap.forecast.dto.ResponseDto;
import com.finleap.forecast.util.TimeStampProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastServiceUnitTest {

    @Autowired
    private ForecastService forecastService;

    @MockBean
    private WeatherRestService weatherRestService;

    @MockBean
    private TimeStampProvider timeStampProvider;

    @Test
    public void testGetWeatherByCity() throws IOException {
        InputStream inJson = ForecastServiceUnitTest.class.getResourceAsStream("/response.json");
        ResponseDto sample = new ObjectMapper().readValue(inJson, ResponseDto.class);

        LocalDate localDate = LocalDate.of(2018, 10, 7);

        when(weatherRestService.getWeatherByCity("München")).thenReturn(sample);
        when(timeStampProvider.getNow()).thenReturn(localDate);

        ForecastDataDto forecastDataDto = forecastService.getAverageFromCity("München");

        assertEquals("14.602666666666721",forecastDataDto.getAverageDaily()+"");
        assertEquals("10.197416666666697",forecastDataDto.getAverageNightly()+"");
        assertEquals("961.5808333333333",forecastDataDto.getAveragePressure()+"");

    }

}
