package ru.dream.team.client.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.db.enitity.UserDto;
import ru.dream.team.client.service.db.repository.DoctorRepository;
import ru.dream.team.client.service.db.repository.PatientRepository;
import ru.dream.team.client.service.db.repository.UserRepository;
import ru.dream.team.client.service.mapper.UserMapper;
import ru.dream.team.client.service.model.DoctorInfo;
import ru.dream.team.client.service.model.PatientInfo;
import ru.dream.team.client.service.model.UserCreds;
import ru.dream.team.client.service.model.admin.response.UserResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public DoctorDto addDoctor(DoctorInfo doctorInfo, UserCreds creds) {
        var doctorDto = doctorRepository.save(userMapper.mapDoctorReqToDto(doctorInfo));

        var userDto = new UserDto();
        userDto.setDoctorDto(doctorDto);
        userDto.setRole(UserDto.Role.ROLE_DOCTOR);
        userDto.setPassword(passwordEncoder.encode(creds.getPassword()));
        userDto.setUsername(creds.getUsername());
        userRepository.save(userDto);

        return doctorDto;
    }

    @Transactional
    public PatientDto addPatient(PatientInfo patientInfo, UserCreds userCreds) {
        var patientDto = patientRepository.save(userMapper.mapPatientReqToPatientDto(patientInfo));

        var userDto = new UserDto();
        userDto.setPatientDto(patientDto);
        userDto.setRole(UserDto.Role.ROLE_PATIENT);
        userDto.setPassword(passwordEncoder.encode(userCreds.getPassword()));
        userDto.setUsername(userCreds.getUsername());

        userRepository.save(userDto);

        return patientDto;
    }

    @Transactional
    public void deleteUser(String username) {
        var user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            try {
                switch (user.get().getRole()) {
                    case ROLE_ADMIN:
                        userRepository.delete(user.get());
                        break;
                    case ROLE_PATIENT:
                        userRepository.delete(user.get());
                        patientRepository.delete(user.get().getPatientDto());
                        break;
                    case ROLE_DOCTOR:
                        userRepository.delete(user.get());
                        doctorRepository.delete(user.get().getDoctorDto());
                        break;
                }
            } catch (Exception ignore) { }
        }
    }

    @Transactional
    public void addDoctorToPatient(long patientId, long doctorId) {
        var patient = patientRepository
            .findById(patientId)
            .orElseThrow(() -> new IllegalArgumentException("Не найден пациент с таким id"));
        var doctor = doctorRepository
            .findById(doctorId)
            .orElseThrow(() -> new IllegalArgumentException("Не найден врач с таким id"));

        patient.addDoctor(doctor);

        patientRepository.save(patient);
    }

    @Transactional
    public String changeUserCredentials(String currentUsername, String newUsername, String newPassword) {
        var user = userRepository
            .findByUsername(currentUsername)
            .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким username не найден"));

        if (newUsername != null && !newUsername.isBlank()) {
            user.setUsername(newUsername);
        }

        if (newPassword != null && !newPassword.isBlank()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        user = userRepository.save(user);

        return user.getUsername();
    }

    @Transactional
    public List<UserResponse> getUsers() {
        return userRepository
            .findAll()
            .stream()
            .filter(user -> !user.getRole().equals(UserDto.Role.ROLE_ADMIN))
            .map(userMapper::mapUserDtoToUserResponse)
            .toList();
    }

    @Transactional
    public List<PatientDto> getPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public List<DoctorDto> getDoctors() {
        return doctorRepository.findAll();
    }
}
