package com.oscngl.spring.boot.kafka.controller;

import com.oscngl.spring.boot.kafka.model.Message;
import com.oscngl.spring.boot.kafka.service.MessageService;
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
    public ResponseEntity<Void> publish(@RequestBody Message message) {
        messageService.publishMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
