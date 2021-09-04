package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;

public class Producer {

    @Autowired
    private KafkaTemplate<String, ProducerDateInfo> kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String topicName;

    public void send() {
        LocalDate localDate = LocalDate.now();
        kafkaTemplate.send(topicName, generateMessage(localDate));
    }

    public ProducerDateInfo generateMessage(LocalDate localDate) {
        return new ProducerDateInfo(localDate.toString(), localDate.isLeapYear());
    }
}
