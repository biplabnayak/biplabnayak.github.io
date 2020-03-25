package io.example.kafka.kafkaproducer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class EventProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    public void produce() throws ExecutionException, InterruptedException {
        ListenableFuture listenableFuture = kafkaTemplate.send("test_topic", "vvvvvvvvvvvvvvvv"+new Date());
        SendResult sendResult = (SendResult)listenableFuture.get();
        log.info("Produced message with | Offset : {} | Partition : {} | Timestamp : {}",
                sendResult.getRecordMetadata().partition(), sendResult.getRecordMetadata().partition(),
                new Date(sendResult.getRecordMetadata().timestamp())
                );
    }
}
