package com.beaconfirebobo.authserver.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {
    private final SimpleDateFormat formatter;

    public DateUtil() {
        formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    }

    public String getCurrentDate() {
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }
}
