package com.finleap.forecast.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.forecast.config.OpenWeathermapApiProperties;
import com.finleap.forecast.dto.ResponseDto;
import com.finleap.forecast.service.ForecastServiceUnitTest;
import com.finleap.forecast.service.WeatherRestService;
import com.finleap.forecast.util.ClientBuilderProvider;
import com.finleap.forecast.util.TimeStampProvider;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WeatherRestServiceUnitTest.TestConfiguration.class)
public class WeatherRestServiceUnitTest {

    @Autowired
    private WeatherRestService weatherRestService;

    @Autowired
    private ClientBuilderProvider clientBuilderProvider;

    @Autowired
    private OpenWeathermapApiProperties openWeathermapApiProperties;


    final Client mockClient = Mockito.mock(Client.class);
    final Response mockResponse = Mockito.mock(Response.class);

    @Configuration
    class TestConfiguration {
        @Bean
        OpenWeathermapApiProperties openWeathermapApiProperties() {
            return Mockito.mock(OpenWeathermapApiProperties.class);
        }

        @Bean
        ClientBuilderProvider clientBuilderProvider() {
            return Mockito.mock(ClientBuilderProvider.class);
        }

        @Bean
        TimeStampProvider timeStampProvider() {
            return Mockito.mock(TimeStampProvider.class);
        }

    }
    @Ignore
    @Test
    public void testGetWeatherByCity() throws IOException {

        InputStream inJson = ForecastServiceUnitTest.class.getResourceAsStream("/response.json");
        ResponseDto expectedResponseDto = new ObjectMapper().readValue(inJson, ResponseDto.class);

        when(openWeathermapApiProperties.getBaseUrl()).thenReturn("url_test");
        when(openWeathermapApiProperties.getKey()).thenReturn("key");

        when(clientBuilderProvider.getClient()).thenReturn(mockClient);

        Mockito.when(this.mockResponse.getStatus()).thenReturn(200);

        final Invocation.Builder mockBuilder = Mockito.mock(Invocation.Builder.class);
        Mockito.when(mockBuilder.get()).thenReturn(this.mockResponse);


        final WebTarget mockWebTarget = Mockito.mock(WebTarget.class);
        Mockito.when(mockWebTarget.path(anyString())).thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.queryParam(anyString(),anyString())).thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.request()).thenReturn(mockBuilder);
        Mockito.when(mockResponse.readEntity(ResponseDto.class)).thenReturn(expectedResponseDto);

        Mockito.when(this.mockClient.target(anyString())).thenReturn(mockWebTarget);

        ResponseDto actualResponseDto = weatherRestService.getWeatherByCity("London");


        assertEquals(actualResponseDto,expectedResponseDto);


    }
}