package io.example.kafka.kafkaconsumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class KafkaEventListener {

    @KafkaListener(topics = "test_topic", groupId = "group_id")
    public void consume(String message) throws InterruptedException {
        System.out.println("Consumed message: " + message);
    }

    @KafkaListener(topics = "test_topic", groupId = "group_id")
    public void consume2(String message) throws InterruptedException {
        System.out.println("Consumed message2: " + message);
    }
}
