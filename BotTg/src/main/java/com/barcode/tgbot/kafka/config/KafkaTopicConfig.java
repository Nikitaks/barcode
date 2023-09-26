package com.barcode.tgbot.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    
    @Value(value = "${spring.kafka.barcode.topics.service-to-bot-topicname}")
    private String messageServiceToTgBotTopicName;
    
    @Value(value = "${spring.kafka.barcode.topics.bot-to-service-topicname}")
    private String tgBotToMessageServiceTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic topic1() {
         return new NewTopic(messageServiceToTgBotTopicName, 1, (short) 1);
    }
    @Bean
    public NewTopic topic2() {
         return new NewTopic(tgBotToMessageServiceTopicName, 1, (short) 1);
    }
}