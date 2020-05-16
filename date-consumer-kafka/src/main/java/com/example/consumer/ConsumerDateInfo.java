package com.example.consumer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsumerDateInfo {
    private String localDate;
    private boolean isLeapYear;
}
