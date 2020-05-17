package com.example.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProducerDateInfo {
    private String localDate;

    @JsonProperty(value="isLeapYear")
    private boolean isLeapYear;
}
