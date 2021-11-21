package com.lx.simple.example.config;

import com.lx.simple.example.consumer.LxMessageConsumer;
import com.lx.simple.example.consumer.MessageConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    @Autowired
    private MessageConsumer messageConsumer;
    @Autowired
    private LxMessageConsumer lxMessageConsumer;

    @Bean
    public MQConsumer consumer() {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("lx_test_simple_consumer");
        // Specify name server addresses.
        consumer.setNamesrvAddr("39.106.46.169:9876");
        // Subscribe订阅 one more more topics to consume.
        try {
            consumer.subscribe("lx_test", "*");
            consumer.registerMessageListener(messageConsumer);
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;
    }

    @Bean
    public MQConsumer lxConsumer() {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("lx_test_simple_lx_consumer");
        // Specify name server addresses.
        consumer.setNamesrvAddr("39.106.46.169:9876");
        // Subscribe订阅 one more more topics to consume.
        try {
            consumer.subscribe("lx_test", "*");
            consumer.registerMessageListener(lxMessageConsumer);
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;
    }
}
