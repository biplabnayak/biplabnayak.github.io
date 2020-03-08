package io.example.cloud.stream.publisher.service;

import io.example.cloud.stream.publisher.binding.AnalyticsBinding;
import io.example.cloud.stream.publisher.model.PageViewEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
public class EventPublisherService {

    @Autowired
    AnalyticsBinding analyticsBinding;

    //@Scheduled(fixedDelay = 5000)
    public void publish() {

        PageViewEvent pageViewEvent = new PageViewEvent();
        pageViewEvent.setId(UUID.randomUUID().toString());
        pageViewEvent.setDuration(new Random().nextLong());
        pageViewEvent.setPage("test");

        log.info("Sending Message : {} to kafka.", pageViewEvent);

        Message<PageViewEvent> message = MessageBuilder.withPayload(pageViewEvent)
                .setHeader(KafkaHeaders.MESSAGE_KEY, pageViewEvent.getId().getBytes()).build();
        analyticsBinding.pageViewOut().send(message);
    }
}
