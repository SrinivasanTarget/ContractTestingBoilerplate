package com.example.consumer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDateInfo {
    private LocalDate localDate;
    private boolean isLeapYear;

    @Override
    public String toString() {
        return "ConsumerDateInfo [LocalDate=" + localDate + ", isLeapYear=" + isLeapYear + "]";
    }
}
