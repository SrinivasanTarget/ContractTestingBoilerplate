package com.example.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsumerDateInfo {
    private String localDate;

    @JsonProperty(value="isLeapYear")
    private boolean isLeapYear;
}
