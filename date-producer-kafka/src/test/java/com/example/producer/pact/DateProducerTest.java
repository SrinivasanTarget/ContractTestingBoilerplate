package com.example.producer.pact;

import au.com.dius.pact.core.model.Interaction;
import au.com.dius.pact.core.model.Pact;
import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.AmqpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.junit5.AmpqTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(PactRunner.class)
@Provider("dateProviderKafka")
@Consumer("dateConsumerKafka")
@PactBroker(host = "localhost", port = "82")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DateProducerTest {

//    @TestTarget
//    public final Target target = new SpringAwareAmqpTarget();

//    @TestTemplate
//    @ExtendWith(PactVerificationInvocationContextProvider.class)
//    void testTemplate(Pact pact, Interaction interaction, PactVerificationContext context) {
//        context.verifyInteraction();
//    }
//
//    @BeforeEach
//    void before(PactVerificationContext context) {
//        context.setTarget(new AmpqTestTarget());
//    }

    @TestTarget
    public final Target target = new AmqpTarget();

    @PactVerifyProvider("valid date from kafka provider")
    public String verifyDateInformationMessage() {
        return "{\"isLeapYear\":true,\"localDate\":\"2000-01-31\"}";
    }
}