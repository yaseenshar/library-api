package com.onpier.libraryapi.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Component
public class DateUtil {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public LocalDate parseDateToLocaleDate(String date) {

        try {
            if (date != null && !date.isEmpty()) {
                return LocalDate.parse(date, formatter);
            }
        } catch (DateTimeParseException dateTimeParseException) {
            log.error("Error occurred while parsing date", dateTimeParseException);
        }

        return null;
    }

}
