package com.oscngl.spring.boot.java.mail.sender.controller;

import com.oscngl.spring.boot.java.mail.sender.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailSenderController {

    private final MailSenderService mailSenderService;

    @GetMapping("/send")
    public ResponseEntity<Void> sendMail(@RequestParam String to,
                                         @RequestParam String text,
                                         @RequestParam String subject) {
        mailSenderService.send(to, text, subject);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
