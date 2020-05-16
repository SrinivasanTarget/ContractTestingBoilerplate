package com.example.consumer.pact;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.Message;
import au.com.dius.pact.core.model.messaging.MessagePact;
import com.example.consumer.ConsumerDateInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "dateProviderKafka", providerType = ProviderType.ASYNCH)
public class DateConsumerTest {

    @Pact(consumer = "dateConsumerKafka")
    public MessagePact validDateMessageFromKafkaProvider(MessagePactBuilder builder) {
        return builder
                .expectsToReceive("valid date from kafka provider")
                .withContent(LambdaDsl.newJsonBody((object) -> {
                    object.dateExpression("localDate", "^\\d{4}-\\d{2}-\\d{2}$","yyyy-MM-dd");
                    object.booleanType("isLeapYear", true);
                }).build())
                .toPact();
    }

    @SneakyThrows
    @Test
    @PactTestFor(pactMethod = "validDateMessageFromKafkaProvider")
    public void testValidDateFromProvider(List<Message> messages) {
        assertThat(messages).isNotEmpty();
        assertThat(new ObjectMapper().readValue(new String(messages.get(0).contentsAsBytes()), ConsumerDateInfo.class))
                .hasFieldOrProperty("localDate")
                .hasFieldOrPropertyWithValue("isLeapYear", true);
    }
}
