package org.jhay.domain.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class DateUtils {
    public static String saveLocalDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss a");
        return date.format(formatter);
    }
}
