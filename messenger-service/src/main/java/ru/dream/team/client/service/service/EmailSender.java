package ru.dream.team.client.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.dream.team.client.service.model.kafka.request.EmailRequest;

@Service
@RequiredArgsConstructor
public class EmailSender {
    private final KafkaTemplate<String, EmailRequest> emailKafkaTemplate;

    public void sendEmail(String to) {
        var request = new EmailRequest();
        request.setTitle("Уведомление о новом сообщении");
        request.setMessage("Вам пришло новое сообщение на платформе X-Ray Express");
        request.setReceiver(to);
        emailKafkaTemplate.send("email", request);
    }
}
