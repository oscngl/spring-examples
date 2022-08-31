package com.oscngl.spring.boot.rabbitmq.rabbitmq.consumer;

import com.oscngl.spring.boot.rabbitmq.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.queues.message}")
    public void consumer(Message message) {
        log.info("RabbitMQ: Message consumed from queue: " + message);
    }

}
