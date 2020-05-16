package com.example.consumer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsumerDateInfo {
    private String localDate;
    private boolean isLeapYear;
}
