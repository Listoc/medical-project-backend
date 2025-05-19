package ru.dream.team.remind.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.dream.team.remind.service.service.EmailRemindSender;

import java.time.OffsetTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class RemindScheduler {
    private final EmailRemindSender emailRemindSender;

    @Scheduled(cron = "30 * * * * *")
    public void send() {
        emailRemindSender.remind(OffsetTime.now().truncatedTo(ChronoUnit.MINUTES));
    }
}
