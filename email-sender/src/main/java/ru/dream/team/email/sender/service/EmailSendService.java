package ru.dream.team.email.sender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSendService {
    private final JavaMailSender javaMailSender;

    public void send(String to, String subject, String text) {
        var message = new SimpleMailMessage();
        message.setFrom("x.ray.express.reminder@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        log.info("Sent message to {}, subject: {}, text: {}", to, subject, text);
    }
}
