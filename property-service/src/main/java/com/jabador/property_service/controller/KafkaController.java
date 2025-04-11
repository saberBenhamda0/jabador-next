package com.jabador.property_service.controller;

import com.jabador.property_service.service.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return "Message published successfully";
    }
}