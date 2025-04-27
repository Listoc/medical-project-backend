package ru.dream.team.remind.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.dream.team.remind.service.config.SchedulerProperties;

@SpringBootApplication
@EnableConfigurationProperties(SchedulerProperties.class)
@EnableScheduling
public class RemindServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RemindServiceApplication.class, args);
	}
}
