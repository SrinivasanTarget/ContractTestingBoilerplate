package com.example.ageconsumer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateResponse {
    private String givenDate;
    private int year;
    private int month;
    private int day;
    private Boolean isValidDate = false;
    private String message;
}
