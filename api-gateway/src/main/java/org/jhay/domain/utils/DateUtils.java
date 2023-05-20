package org.jhay.domain.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static  String saveLocalDate(LocalDateTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss a");
        return time.format(formatter);
    }
}
