package io.example.kafka.kafkaproducer.admin;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import scala.collection.JavaConversions;

import java.util.Arrays;
import java.util.Properties;

public class TopicCreatorCluster {

    public static void main(String[] args) {
        // localhost:2181,localhost:2182,localhost:2183
        ZkUtils zkUtils = ZkUtils.apply("localhost:2181,localhost:2182,localhost:2183", 10000, 10000, false);
        AdminUtils.createTopic(zkUtils, "test_topic", 3, 3, new Properties(), RackAwareMode.Disabled$.MODULE$);
        System.out.println(AdminUtils.fetchAllTopicConfigs(zkUtils));
        System.out.println(zkUtils.getPartitionAssignmentForTopics(
                JavaConversions.asScalaBuffer(Arrays.asList("test1"))));

    }
}
