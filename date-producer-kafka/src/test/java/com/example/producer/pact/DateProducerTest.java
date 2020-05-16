package com.example.producer.pact;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.AmqpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.example.producer.ProducerDateInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(PactRunner.class)
@Provider("dateProviderKafka")
@Consumer("dateConsumerKafka")
@PactBroker(host = "localhost", port = "8282")
public class DateProducerTest {

    @TestTarget
    public final Target target = new AmqpTarget();

    @SneakyThrows
    @PactVerifyProvider("valid date from kafka provider")
    public String verifyDateInformationMessage() {
        return new ObjectMapper().writeValueAsString(new ProducerDateInfo(LocalDate.now().toString(),
                true));
    }
}