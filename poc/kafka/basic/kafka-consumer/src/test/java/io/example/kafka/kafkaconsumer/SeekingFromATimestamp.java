package io.example.kafka.kafkaconsumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;


import java.util.HashMap;
import java.util.Map;


/**
 * Kafka Consumer to seek messages from a timestamp
 *
 * @author Biplab Nayak
 */
public class SeekingFromATimestamp {

    private static String TOPIC = "test_topic";
    private static long SEEK_DURATION = 5 * 60 * 1000;

    public static void main(String[] args) {
        Consumer<Long, String> consumer = StandAloneConsumer.createConsumer();

        Map<TopicPartition, Long> partitions= new HashMap<>(3);
        TopicPartition p0 = new TopicPartition(TOPIC, 0);
        TopicPartition p1 = new TopicPartition(TOPIC, 1);
        TopicPartition p2 = new TopicPartition(TOPIC, 2);
        Long time = System.currentTimeMillis() - SEEK_DURATION;
        partitions.put(p0, time);
        partitions.put(p1, time);
        partitions.put(p2, time);

        Map<TopicPartition, OffsetAndTimestamp> offset = consumer.offsetsForTimes(partitions);

        consumer.assign(offset.keySet());
        for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : offset.entrySet()) {
            consumer.seek(entry.getKey(), entry.getValue().offset());
        }

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(1000);
            if (consumerRecords.count() == 0) {
                continue;
            }
            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
                consumer.commitSync();
        });
        }
    }
}
