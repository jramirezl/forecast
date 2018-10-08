package com.finleap.forecast.service;

import com.finleap.forecast.config.OpenWeathermapApiProperties;
import com.finleap.forecast.dto.ResponseDto;
import com.finleap.forecast.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherRestServiceTest {


    @Autowired
    private OpenWeathermapApiProperties openWeathermapApiProperties;

    @Autowired
    private TestRestTemplate testRestTemplate;

;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {

        String param = "?"+Constants.ID +"=London&"+Constants.APPID+"="+openWeathermapApiProperties.getKey();
        ResponseDto responseDto =  this.testRestTemplate.getForObject(openWeathermapApiProperties.getBaseUrl() + param,
                ResponseDto.class);
        assertTrue(responseDto.getCity().getName().contains("London"));
    }

}
