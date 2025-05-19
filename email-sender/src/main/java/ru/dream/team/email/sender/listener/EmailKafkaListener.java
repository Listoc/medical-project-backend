package ru.dream.team.email.sender.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.dream.team.email.sender.model.EmailRequest;
import ru.dream.team.email.sender.service.EmailSendService;

@Component
@RequiredArgsConstructor
public class EmailKafkaListener {
    private final EmailSendService emailSendService;

    @KafkaListener(id = "2", topics = "email", containerFactory = "kafkaListenerEmailContainerFactory")
    public void listenEmail(EmailRequest emailRequest) {
        emailSendService.send(emailRequest.getReceiver(), emailRequest.getTitle(), emailRequest.getMessage());
    }
}
