package io.example.cloud.stream.publisher.service;

import io.example.cloud.stream.publisher.binding.AnalyticsBinding;
import io.example.cloud.stream.publisher.model.PageViewEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumerService {

    @StreamListener
    public void process(@Input(AnalyticsBinding.PAGE_VIEW_IN)KStream<String, PageViewEvent> events) {
        events.foreach((key, val) -> {
            log.info("Received message : {} ", val);
            //throw new RuntimeException();
        });
    }

}
