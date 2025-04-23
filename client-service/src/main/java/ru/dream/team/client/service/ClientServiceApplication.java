package ru.dream.team.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dream.team.client.service.db.enitity.User;
import ru.dream.team.client.service.db.repository.UserRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class ClientServiceApplication implements CommandLineRunner {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var user = new User();
		user.setPassword(passwordEncoder.encode("admin"));
		user.setUsername("admin");
		user.setRole(User.Role.ROLE_ADMIN);
		if (userRepository.findByUsername("admin").isEmpty()) {
			userRepository.save(user);
		}
	}
}
