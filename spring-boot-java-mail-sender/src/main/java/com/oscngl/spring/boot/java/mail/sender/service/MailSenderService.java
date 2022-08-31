package com.oscngl.spring.boot.java.mail.sender.service;

public interface MailSenderService {

    void send(String to, String text, String subject);

}
