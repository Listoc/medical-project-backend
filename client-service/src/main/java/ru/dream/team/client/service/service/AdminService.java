package ru.dream.team.client.service.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dream.team.client.service.db.enitity.Doctor;
import ru.dream.team.client.service.db.enitity.Patient;
import ru.dream.team.client.service.db.enitity.User;
import ru.dream.team.client.service.db.repository.DoctorRepository;
import ru.dream.team.client.service.db.repository.PatientRepository;
import ru.dream.team.client.service.db.repository.UserRepository;

import java.util.List;

@Service
public class AdminService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(PatientRepository patientRepository, DoctorRepository doctorRepository, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public String addDoctor(Doctor doctor, String username, String password) {
        User user;

        var userFromDB = userRepository.findByUsername(username);

        if (userFromDB.isPresent()) {
            return "username";
        }

        var doctorFromDB = doctorRepository.findByEmail(doctor.getEmail());

        if (doctorFromDB.isPresent()) {
            return "email";
        }

        doctorRepository.save(doctor);
        user = new User();
        user.setDoctor(doctor);
        user.setRole(User.Role.ROLE_DOCTOR);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userRepository.save(user);

        return "good";
    }

    @Transactional
    public String addPatient(Patient patient, String username, String password) {
        User user;

        var userFromDB = userRepository.findByUsername(username);

        if (userFromDB.isPresent()) {
            return "username";
        }

        var patientFromDB = patientRepository.findByEmail(patient.getEmail());

        if (patientFromDB.isPresent()) {
            return "email";
        }

        patientRepository.save(patient);
        user = new User();
        user.setPatient(patient);
        user.setRole(User.Role.ROLE_PATIENT);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);

        userRepository.save(user);

        return "good";
    }

    @Transactional
    public List<String> getUsernames() {
        return userRepository.findAll().stream().filter(user -> !user.getRole().equals(User.Role.ROLE_ADMIN)).map(User::getUsername).toList();
    }

    @Transactional
    public boolean addDoctorToPatient(long patientId, long doctorId) {
        var patient = patientRepository.findById(patientId);
        var doctor = doctorRepository.findById(doctorId);

        if (patient.isEmpty()) {
            return false;
        }

        if (doctor.isEmpty()) {
            return false;
        }

        patient.get().addDoctor(doctor.get());

        doctorRepository.save(doctor.get());
        patientRepository.save(patient.get());

        return true;
    }

    @Transactional
    public boolean changeUserCredentials(String currentUsername, String newUsername, String newPassword) {
        var user = userRepository.findByUsername(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (newUsername != null) {
            user.setUsername(newUsername);
        }

        if (newPassword != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepository.save(user);

        return true;
    }

    @Transactional
    public boolean deleteUser(String username) {
        var user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getRole() == User.Role.ROLE_ADMIN) {
            return false;
        }

        user.ifPresent(userRepository::delete);

        return true;
    }

    @Transactional
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }
}
