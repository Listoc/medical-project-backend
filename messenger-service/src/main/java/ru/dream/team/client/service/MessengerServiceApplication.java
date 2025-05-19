package ru.dream.team.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class MessengerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessengerServiceApplication.class, args);
	}
}
