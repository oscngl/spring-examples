package com.oscngl.spring.boot.rabbitmq.controller;

import com.oscngl.spring.boot.rabbitmq.model.Message;
import com.oscngl.spring.boot.rabbitmq.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> publishMessage(@RequestBody Message message) {
        messageService.publishMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
