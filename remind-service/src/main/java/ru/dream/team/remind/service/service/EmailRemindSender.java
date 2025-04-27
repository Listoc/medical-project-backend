package ru.dream.team.remind.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dream.team.remind.service.model.EmailRequest;

import java.time.OffsetTime;

@Service
@RequiredArgsConstructor
public class EmailRemindSender {
    private final RemindService remindService;
    private final KafkaTemplate<String, EmailRequest> emailKafkaTemplate;

    @Transactional
    public void sendEmail(EmailRequest request) {
        emailKafkaTemplate.send("email", request);
    }

    public void remind(OffsetTime remindTime) {
        var reminds = remindService.getReminds(remindTime);
        for (var remind : reminds) {
            var request = new EmailRequest();
            request.setTitle("Напоминание о необходимости принять лекарства");
            request.setMessage("Напоминание о необходимости принять лекарства");
            request.setReceiver(remind.getPatient().getEmail());
            emailKafkaTemplate.send("email", request);
        }
    }
}
