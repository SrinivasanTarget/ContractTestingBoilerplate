package com.example.producer.pact;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.AmqpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("dateProviderKafka")
@Consumer("dateConsumerKafka")
@PactBroker(host = "localhost", port = "8282")
public class DateProducerTest {

    @TestTarget
    public final Target target = new AmqpTarget();

    @PactVerifyProvider("valid date from kafka provider")
    public String verifyDateInformationMessage() {
        return "{\"isLeapYear\":true,\"localDate\":\"2000-01-31\"}";
    }
}