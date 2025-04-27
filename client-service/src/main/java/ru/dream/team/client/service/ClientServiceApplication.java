package ru.dream.team.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.Gender;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.db.enitity.UserDto;
import ru.dream.team.client.service.db.repository.DoctorRepository;
import ru.dream.team.client.service.db.repository.PatientRepository;
import ru.dream.team.client.service.db.repository.UserRepository;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class ClientServiceApplication implements CommandLineRunner {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var user = new UserDto();

		try {
			user.setPassword(passwordEncoder.encode("admin"));
			user.setUsername("admin");
			user.setRole(UserDto.Role.ROLE_ADMIN);

			userRepository.save(user);
		} catch (Exception ignore) { }

		try {
			user = new UserDto();
			user.setPassword(passwordEncoder.encode("patient"));
			user.setUsername("patient");
			user.setRole(UserDto.Role.ROLE_PATIENT);

			var patientDto = new PatientDto();
			patientDto.setEmail("patient@email.com");
			patientDto.setFirstName("Вася");
			patientDto.setLastName("Пупкин");
			patientDto.setMiddleName("Васильевич");
			patientDto.setBirthday(LocalDate.of(2002, 2, 2));
			patientDto.setDiagnosis("Пневмония");
			patientDto.setSex(Gender.MALE);
			user.setPatientDto(patientDto);
			patientRepository.save(patientDto);

			userRepository.save(user);
		} catch (Exception ignore) { }

		try {
			user = new UserDto();
			user.setPassword(passwordEncoder.encode("doctor"));
			user.setUsername("doctor");
			user.setRole(UserDto.Role.ROLE_DOCTOR);

			var doctorDto = new DoctorDto();
			doctorDto.setEmail("doctor@email.com");
			doctorDto.setFirstName("Иван");
			doctorDto.setLastName("Иванов");
			doctorDto.setMiddleName("Иванович");
			doctorDto.setBirthday(LocalDate.of(2002, 2, 2));
			doctorDto.setCategory("1-я категория");
			doctorDto.setSpecialization("Терапия");
			doctorDto.setSex(Gender.MALE);
			user.setDoctorDto(doctorDto);

			doctorRepository.save(doctorDto);
			userRepository.save(user);
		} catch (Exception ignore) { }
	}
}
