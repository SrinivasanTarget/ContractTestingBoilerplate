package com.example.ageconsumer.integration;

import com.atlassian.ta.wiremockpactgenerator.WireMockPactGenerator;
import com.example.ageconsumer.AgeApplication;
import com.example.ageconsumer.DateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.response.ValidatableResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = AgeApplication.class)
public class AgeConsumerTest {
    WireMockServer wireMockServer = null;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void startServer() {
        wireMockServer = new WireMockServer();
        WireMockConfiguration.options().dynamicPort().notifier(new ConsoleNotifier(true));
        wireMockServer.addMockServiceRequestListener(
                WireMockPactGenerator.builder("the-consumer", "the-provider")
                        .build());
        wireMockServer.start();
        baseUrl = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @SneakyThrows
    @Test
    public void shouldCalculateAgeForAGivenValidBirthDate() {
        final DateResponse dateResponse = DateResponse.builder().day(3).givenDate("2001-02-03")
                .isValidDate(true)
                .message("hello")
                .month(2)
                .year(2001).build();
        String response = new ObjectMapper().writeValueAsString(dateResponse);
        stubGetWithString(response);

        final ValidatableResponse validatableResponse = given()
                .contentType("application/json")
                .when()
                .get(baseUrl + "/age-calculate?birthDate=2001-02-03")
                .prettyPeek()
                .then();
        validatableResponse
                .statusCode(200);
    }

    public void stubGetWithString(String json) {
        wireMockServer.stubFor(WireMock.any(WireMock.anyUrl())
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("content-type", "application/json")
                        .withBody(json)
                ));
    }
}
