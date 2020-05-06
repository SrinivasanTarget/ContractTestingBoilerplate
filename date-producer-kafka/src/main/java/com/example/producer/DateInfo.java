package com.example.producer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DateInfo {
    private LocalDate localDate;
    private boolean isLeapYear;
}
