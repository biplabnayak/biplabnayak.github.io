package io.example.cloud.stream.publisher.binding;


import io.example.cloud.stream.publisher.model.PageViewEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AnalyticsBinding {

    String PAGE_VIEW_OUT = "pvout";

    String PAGE_VIEW_IN = "pvin";

    @Output(PAGE_VIEW_OUT)
    MessageChannel pageViewOut() ;

    @Input(PAGE_VIEW_IN)
    KStream<String, PageViewEvent> pageViewIn();
}
