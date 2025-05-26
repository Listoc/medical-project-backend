package ru.dream.team.client.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.dream.team.client.service.model.kafka.request.EmailRequest;

@Service
@RequiredArgsConstructor
public class EmailSender {
    private final KafkaTemplate<String, EmailRequest> emailKafkaTemplate;

    public void sendEmail(String diagnosis, String to) {
        if (!diagnosis.equals("здоров")) {
            var request = new EmailRequest();
            request.setTitle("Уведомление о новом снимке");
            request.setMessage("Ваш пациент загрузил рентгеновский снимок X-Ray Express! Предварительный диагноз - " + diagnosis);
            request.setReceiver(to);
            emailKafkaTemplate.send("email", request);
        }
    }
}
