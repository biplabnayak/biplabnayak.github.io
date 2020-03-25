package io.example.cloud.stream.publisher.model;

import lombok.Data;

@Data
public class PageViewEvent {

    String id, page;
    Long duration;
}
