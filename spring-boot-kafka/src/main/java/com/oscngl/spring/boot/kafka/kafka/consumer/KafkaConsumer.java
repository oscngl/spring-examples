package com.oscngl.spring.boot.kafka.kafka.consumer;

import com.oscngl.spring.boot.kafka.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "oscngl", groupId = "groupId")
    void listener(Message message) {
        log.info("Kafka: Message received from queue: " + message);
    }

}
