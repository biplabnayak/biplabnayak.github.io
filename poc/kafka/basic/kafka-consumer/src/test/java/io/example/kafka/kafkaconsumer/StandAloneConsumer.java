package io.example.kafka.kafkaconsumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class StandAloneConsumer {

    public static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9093,localhost:9094,localhost:9095");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // Create the consumer using props.
        final Consumer<Long, String> consumer =
                new KafkaConsumer<Long, String>(props);
        // Subscribe to the topic.
        //consumer.subscribe(Collections.singletonList("test_topic"));
        return consumer;
    }

    public static void main(String[] args) {
        Consumer<Long, String> consumer = createConsumer();
        final int giveUp = 100;   int noRecordsCount = 0;
        //consumer.poll(Duration.ofSeconds(1));
        //consumer.seekToBeginning(Arrays.asList(new TopicPartition("test_topic", 0)));


        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(1000);
            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }
            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
                if (record.offset() % 2 == 0) {
                    System.out.println("Committing message with partition : " + record.partition() + ", offset : " + record.offset());
                    consumer.commitSync();
                } else {
                    System.out.println("Skipped Committing message with partition : " + record.partition() + ", offset : " + record.offset());
                }
            });
        }
        consumer.close();
    }
}
