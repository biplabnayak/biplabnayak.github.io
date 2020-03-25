package io.example.cloud.stream.publisher;

import io.example.cloud.stream.publisher.binding.AnalyticsBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBinding({AnalyticsBinding.class})
@EnableScheduling
public class CloudStreamPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamPublisherApplication.class, args);
	}

}
