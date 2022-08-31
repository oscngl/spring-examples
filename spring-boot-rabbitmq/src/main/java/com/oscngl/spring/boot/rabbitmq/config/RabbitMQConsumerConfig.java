package com.oscngl.spring.boot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.message}")
    private String messageQueue;

    @Value("${rabbitmq.routing-keys.internal-message}")
    private String internalMessageRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(this.messageQueue);
    }

    @Bean
    public Binding internalToMessageBinding() {
        return BindingBuilder
                .bind(messageQueue())
                .to(internalTopicExchange())
                .with(this.internalMessageRoutingKey);
    }

}
