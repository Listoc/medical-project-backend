package ru.dream.team.remind.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    private final SchedulerProperties schedulerProperties;

    public ApplicationConfig(SchedulerProperties schedulerProperties) {
        this.schedulerProperties = schedulerProperties;
    }

    @Bean
    public SchedulerProperties.Scheduler scheduler() {
        return schedulerProperties.scheduler();
    }
}
