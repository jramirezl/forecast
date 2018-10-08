package com.finleap.forecast.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToDate {

    public static LocalDateTime convert(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return  dateTime;
    }

}
