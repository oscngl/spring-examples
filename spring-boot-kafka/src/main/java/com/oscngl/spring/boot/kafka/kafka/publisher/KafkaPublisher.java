package com.oscngl.spring.boot.kafka.kafka.publisher;

import com.oscngl.spring.boot.kafka.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishMessage(Message message) {
        kafkaTemplate.send("oscngl", message.getMessage());
        log.info("Kafka: Message sent to queue: " + message);
    }

}
