package com.example.producer.pact;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit5.MessageTestTarget;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import com.example.producer.Producer;
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
    public final MessageTestTarget target = new MessageTestTarget();

    @SneakyThrows
    @PactVerifyProvider("valid date from kafka provider")
    public String verifyDateInformationMessage() {
        return new ObjectMapper().writeValueAsString(new Producer().generateMessage(LocalDate.now()));
    }
}