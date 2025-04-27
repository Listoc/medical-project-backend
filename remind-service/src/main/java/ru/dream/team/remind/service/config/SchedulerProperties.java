package ru.dream.team.remind.service.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app")
public record SchedulerProperties(
        Scheduler scheduler
) {
    public record Scheduler(@NotNull Duration interval) {}
}
