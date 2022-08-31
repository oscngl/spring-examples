package com.oscngl.spring.boot.rabbitmq.service.impl;

import com.oscngl.spring.boot.rabbitmq.model.Message;
import com.oscngl.spring.boot.rabbitmq.rabbitmq.publisher.RabbitMQPublisher;
import com.oscngl.spring.boot.rabbitmq.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RabbitMQPublisher messagePublisher;

    @Override
    public void publishMessage(Message message) {
        messagePublisher.publishMessage(message);
        log.info("RabbitMQ: Message published to queue: " + message);
    }

}
