package io.example.kafka.kafkaconsumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventListener {

    @KafkaListener(topics = "test_topic", groupId = "group_id")
    public void consume(ConsumerRecord<Long, String> consumerRecord) throws InterruptedException {
        System.out.println("Consumed message: " + consumerRecord.toString());
    }

    @KafkaListener(topics = "test_topic", groupId = "group_id")
    public void consume2(String message) throws InterruptedException {
        System.out.println("Consumed message2: " + message);
    }
}
