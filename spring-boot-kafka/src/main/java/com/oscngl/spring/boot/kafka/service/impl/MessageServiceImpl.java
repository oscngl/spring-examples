package com.oscngl.spring.boot.kafka.service.impl;

import com.oscngl.spring.boot.kafka.kafka.publisher.KafkaPublisher;
import com.oscngl.spring.boot.kafka.model.Message;
import com.oscngl.spring.boot.kafka.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final KafkaPublisher messagePublisher;

    @Override
    public void publishMessage(Message message) {
        messagePublisher.publishMessage(message);
    }

}
