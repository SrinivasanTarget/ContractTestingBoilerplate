package com.example.ageconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AgeService {

    @Value("${dateproviderservice.baseurl}")
    private String dateProviderServiceURL;

    private RestTemplate restTemplate;

    public AgeResponse calculateAge(String birthDate) {
        String uri = UriComponentsBuilder.fromHttpUrl(dateProviderServiceURL)
                .path("provider/validDate")
                .queryParam("date", birthDate)
                .toUriString();
        restTemplate = new RestTemplate();
        DateResponse dateResponse = restTemplate.getForObject(uri, DateResponse.class);
        assert dateResponse != null;
        if(dateResponse.getIsValidDate()) {
            LocalDate birthday = LocalDate.of(dateResponse.getYear(), dateResponse.getMonth(), dateResponse.getDay());

            Period period = Period.between(birthday, LocalDate.now());
            return new AgeResponse(period.getDays(), period.getMonths(), period.getYears());
        } else {
            return null;
        }
    }
}
