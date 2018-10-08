package com.finleap.forecast.util;


import java.time.LocalDate;

public interface TimeStampProvider {
    LocalDate getNow();
}
