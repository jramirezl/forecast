package com.finleap.forecast.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TimeStampProviderImpl implements TimeStampProvider {
    @Override
    public LocalDate getNow() {
        return LocalDate.now();
    }
}
