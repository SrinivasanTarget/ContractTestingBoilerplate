package com.example.dateprovider;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class DateService {

    public DateResponse getValidDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return new DateResponse(localDate.toString(), localDate.getYear(), localDate.getMonth().getValue(),
                    localDate.getDayOfMonth(), true, "date parsed successfully");
        } catch (DateTimeParseException e) {
            final DateResponse dateResponse = new DateResponse();
            dateResponse.setIsValidDate(false);
            dateResponse.setGivenDate(date);
            dateResponse.setMessage(date + " is not a valid date format");
            return dateResponse;
        }
    }
}
