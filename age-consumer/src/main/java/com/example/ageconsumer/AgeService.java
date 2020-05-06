package com.example.ageconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AgeService {

    @Value("${dateproviderservice.baseurl}")
    private String dateProviderServiceURL;

    public AgeResponse calculateAge(String birthDate) {
        String uri = UriComponentsBuilder.fromHttpUrl(dateProviderServiceURL)
                .path("provider/validDate")
                .queryParam("date", birthDate)
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        ResponseEntity<DateResponse> responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET,
                new HttpEntity<>(headers), DateResponse.class);
        assert responseEntity.getBody() != null;
        if(responseEntity.getBody().getIsValidDate()) {
            LocalDate birthday = LocalDate.of(responseEntity.getBody().getYear(), responseEntity.getBody().getMonth(), responseEntity.getBody().getDay());

            Period period = Period.between(birthday, LocalDate.now());
            return new AgeResponse(period.getDays(), period.getMonths(), period.getYears());
        } else {
            return null;
        }
    }
}
