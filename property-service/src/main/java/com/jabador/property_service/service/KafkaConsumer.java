package com.jabador.property_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "property", groupId = "mygroup-id")
    public void consume(String message){
        System.out.println(message);
    }
}
