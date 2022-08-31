package com.oscngl.spring.boot.kafka.service;

import com.oscngl.spring.boot.kafka.model.Message;

public interface MessageService {

    void publishMessage(Message message);

}
