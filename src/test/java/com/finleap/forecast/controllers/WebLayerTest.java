package com.finleap.forecast.controllers;

import com.finleap.forecast.controller.ForecastController;
import com.finleap.forecast.dto.ForecastDataDto;
import com.finleap.forecast.service.ForecastService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ForecastController.class)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ForecastService forecastService;


    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        ForecastDataDto forecastDataDto = ForecastDataDto.builder().averageDaily(14D).averageDaily(10D).averagePressure(6D).build();

        when(forecastService.getAverageFromCity("LONDON")).thenReturn(forecastDataDto);


        this.mockMvc.perform(get("/data?CITY=LONDON"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"averageDaily\":10.0,\"averageNightly\":0.0,\"averagePressure\":6.0}")));
    }
}
