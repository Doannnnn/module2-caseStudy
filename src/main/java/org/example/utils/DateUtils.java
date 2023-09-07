package org.example.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
    public static LocalDate parseDate(String strDate) {
        try {
            return LocalDate.parse(strDate, dateTimeFormatter);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return null;
    }

    public static String formatDate(LocalDate localDate) {
        try{
            return dateTimeFormatter.format(localDate);
        }catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return null;
    }
    public static LocalDateTime parseDateTime(String strDate) {
        try {
            return LocalDateTime.parse(strDate, dateTimeFormat);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String formatDateTime(LocalDateTime localDateTime) {
        try {
            return dateTimeFormat.format(localDateTime);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
        }
        return null;
    }
}

