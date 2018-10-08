package com.finleap.forecast.controller;

import com.finleap.forecast.dto.ForecastDataDto;
import com.finleap.forecast.service.ForecastService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForecastController {

    ForecastService forecastService;

    @Autowired
    ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ApiOperation(
            value = "Retrieves the forecast average",
            notes = "Average of daily (6h-18h) temperature, in Celsius, for the following 3 days, Average of nightly (18h-6h) temperature and the Average of pressure for the following 3 days")
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Calculate and getting the data from openweathermap the was ok"),
            @ApiResponse(code = 400, message = "VAlidation to the city field fails"),
            @ApiResponse(code = 500, message = "Error getting the data from openweathermap ")})
    public ResponseEntity<?> get(
            @ApiParam(value = "name of teh city, should be a existing city, length greate than 0", required = true) @RequestParam("CITY") String cityName) {

        if (!cityName.equals("")) {
            try {
                return ResponseEntity.ok(forecastService.getAverageFromCity(cityName));
            } catch (Exception ex) {
                log.error(ForecastController.class.getName(), ex);
                return new ResponseEntity<String>("Some error occurred at server", HttpStatus.INTERNAL_SERVER_ERROR);

            }
        } else {
            return new ResponseEntity<String>("The length of the field city should be greater than 0", HttpStatus.BAD_REQUEST);
        }
    }


}
