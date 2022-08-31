package com.oscngl.spring.boot.rabbitmq.service;

import com.oscngl.spring.boot.rabbitmq.model.Message;

public interface MessageService {

    void publishMessage(Message message);

}
