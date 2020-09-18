package io.example.kafka.kafkaproducer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class EventProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    public void produce() throws ExecutionException, InterruptedException {

        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageHeaders.TIMESTAMP, new Date());
        headers.put(KafkaHeaders.TOPIC, "test_topic");
        headers.put("customHeader", "Custom Header");

        String data = "vvvvvvvvvvvvvvvv" + new Date();

        Message<String> message = new GenericMessage<>(data, headers);

        ListenableFuture listenableFuture = kafkaTemplate.send(message);

        SendResult sendResult = (SendResult)listenableFuture.get();
        log.info("Produced message with | Offset : {} | Partition : {} | Timestamp : {}",
                sendResult.getRecordMetadata().offset(), sendResult.getRecordMetadata().partition(),
                new Date(sendResult.getRecordMetadata().timestamp())
                );
    }
}
