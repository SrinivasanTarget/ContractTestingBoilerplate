package com.example.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    public ConsumerDateInfo consumerDateInfo;

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @SneakyThrows
    @KafkaListener(topics = "testing")
    public void receive(ConsumerDateInfo consumerDateInfo) {
        String consumerInfo = new ObjectMapper().writeValueAsString(consumerDateInfo);
        LOGGER.info("received consumer='{}'", consumerInfo);
        this.consumerDateInfo = consumerDateInfo;
    }
}
