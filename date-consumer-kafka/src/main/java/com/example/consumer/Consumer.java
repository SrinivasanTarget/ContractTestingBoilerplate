package com.example.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @SneakyThrows
    @KafkaListener(topics = "testing")
    public void receive(ConsumerDateInfo consumerDateInfo) {
        LOGGER.info("received car='{}'", new ObjectMapper().writeValueAsString(consumerDateInfo));
    }
}
