package com.biplab.camelboot;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class KafkaService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    KafkaConsumer<String, String> kafkaConsumer;

    @PostConstruct
    public void send() {

        kafkaTemplate.send("test_topic", "Test Data " + new Date());

    }

    @KafkaListener(topics = "test_topic", groupId = "default_id")
    public void consume(ConsumerRecords consumerRecord) {

        System.out.println(consumerRecord);
    }
}
