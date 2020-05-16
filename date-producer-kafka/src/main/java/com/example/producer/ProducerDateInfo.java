package com.example.producer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProducerDateInfo {
    private String localDate;
    private boolean isLeapYear;
}
