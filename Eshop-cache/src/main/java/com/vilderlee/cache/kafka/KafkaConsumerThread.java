package com.vilderlee.cache.kafka;

import com.vilderlee.cache.listener.ApplicationContext;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.record.KafkaLZ4BlockInputStream;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.kafka
 * @auther vilderlee
 * @date 2019/11/9 8:38 下午
 */

public class KafkaConsumerThread implements Runnable {

    private String topic;
    private ExecutorService executor;


    public KafkaConsumerThread(String topic) {
        this.topic = topic;
        this.executor = Executors.newFixedThreadPool(5);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);
        org.apache.kafka.clients.consumer.KafkaConsumer kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer(createConsumerConfig());

        kafkaConsumer.subscribe(Arrays.asList(topic));

        ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.of(1000, ChronoUnit.SECONDS));
        while (true) {
            poll.forEach(consumerRecord -> {
                executor.execute(new KafkaMessageProcessor(consumerRecord));
            });
        }

    }


    private static Properties createConsumerConfig() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "192.168.33.11:2181,192.168.33.12:2181,192.168.33.13:2181");
        properties.put("bootstrap.servers", "192.168.33.11:9092,192.168.33.12:9092,192.168.33.13:9092");
        properties.put("group.id", "eshop-cache-group");
        properties.put("zookeeper.session.timeout.ms", "400");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;

    }
}
