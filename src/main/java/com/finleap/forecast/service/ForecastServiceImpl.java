package com.finleap.forecast.service;

import com.finleap.forecast.dto.ForecastDataDto;
import com.finleap.forecast.dto.Items;
import com.finleap.forecast.dto.ResponseDto;
import com.finleap.forecast.util.TimeStampProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import static com.finleap.forecast.util.Constants.KELVIN_TO_CELCIUS;

@Slf4j
@Service
public class ForecastServiceImpl implements ForecastService {

    private final WeatherRestService weatherRestService;
    private final TimeStampProvider timeStampProvider;

    @Autowired
    public ForecastServiceImpl(WeatherRestService weatherRestService, TimeStampProvider timeStampProvider) {
        this.weatherRestService = weatherRestService;
        this.timeStampProvider = timeStampProvider;
    }

    @Override
    @Cacheable("data")
    public ForecastDataDto getAverageFromCity(String city) {

        try {
            ResponseDto responseDto = weatherRestService.getWeatherByCity(city);

            log.info(responseDto.toString());

            return setUpForecast(responseDto);

        } catch (Exception e) {
            log.error(ForecastServiceImpl.class.toString(), e);
        }

        return null;
    }


    private ForecastDataDto setUpForecast(ResponseDto responseDto) {

        List<Items> listAll = responseDto.getList().stream().filter(it -> filterDates(it.getDate())).collect(Collectors.toList());
        List<Items> listDaily = listAll.stream().filter(it -> filterDaily(it.getDate())).collect(Collectors.toList());
        List<Items> listNigthly = listAll.stream().filter(it -> filterNightly(it.getDate())).collect(Collectors.toList());

        double averagePressure = listDaily.stream().mapToDouble(p -> p.getMain().getPressure()).average().getAsDouble();
        double averageDaily = listDaily.stream().mapToDouble(p -> p.getMain().getTemp()).average().getAsDouble();
        double averageNightly = listNigthly.stream().mapToDouble(p -> p.getMain().getTemp()).average().getAsDouble();

        return ForecastDataDto.builder().averageDaily(averageDaily - KELVIN_TO_CELCIUS).averageNightly(averageNightly - KELVIN_TO_CELCIUS).averagePressure(averagePressure).build();
    }

    private boolean filterDates(LocalDateTime localDateTime) {
        LocalDate today = timeStampProvider.getNow();
        LocalDate dateToEvaluate = localDateTime.toLocalDate();
        Period p = Period.between(today, dateToEvaluate);
        return p.getDays() <= 3 && p.getDays() > 0;
    }


    private boolean filterDaily(LocalDateTime localDateTime) {
        LocalDate dateToEvaluate = localDateTime.toLocalDate();
        LocalDateTime start = dateToEvaluate.atTime(06, 00, 00);
        LocalDateTime stop = dateToEvaluate.atTime(18, 00, 00);
        return (!localDateTime.isBefore(start)) && localDateTime.isBefore(stop);
    }


    private boolean filterNightly(LocalDateTime localDateTime) {
        LocalDate dateToEvaluate = localDateTime.toLocalDate();
        LocalDateTime start1 = dateToEvaluate.atTime(00, 00, 00);
        LocalDateTime stop1 = dateToEvaluate.atTime(06, 00, 00);

        LocalDateTime start2 = dateToEvaluate.atTime(18, 00, 00);
        LocalDateTime stop2 = dateToEvaluate.atTime(23, 59, 00);
        return (!localDateTime.isBefore(start1)) && localDateTime.isBefore(stop1) || (!localDateTime.isBefore(start2)) && localDateTime.isBefore(stop2);
    }

}
