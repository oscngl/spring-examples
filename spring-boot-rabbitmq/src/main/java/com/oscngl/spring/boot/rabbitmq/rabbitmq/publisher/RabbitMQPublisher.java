package com.oscngl.spring.boot.rabbitmq.rabbitmq.publisher;

import com.oscngl.spring.boot.rabbitmq.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQPublisher {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchanges.internal}")
    private String EXCHANGE;

    @Value("${rabbitmq.routing-keys.internal-message}")
    private String ROUTING_KEY;

    public void publishMessage(Message message) {
        amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
        log.info("RabbitMQ: Message sent to queue: " + message);
    }

}
