package ru.dream.team.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ImageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageServiceApplication.class, args);
	}
}
