package com.jabador.property_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaConfigTopic {

    @Bean
    public NewTopic serviceTopic() {
        return TopicBuilder.name("property-service").build();
    }
}
