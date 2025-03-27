package ru.dream.team.client.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dream.team.client.service.db.enitity.User;
import ru.dream.team.client.service.model.AddDoctorRequest;
import ru.dream.team.client.service.model.AddDoctorToPatientRequest;
import ru.dream.team.client.service.model.AddPatientRequest;
import ru.dream.team.client.service.model.ChangeCredentialsRequest;
import ru.dream.team.client.service.service.AdminService;

@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/doctor")
    @SecurityRequirement(name = "Authorization")
    @Operation(summary = "Создать уз врача")
    public ResponseEntity<?> addDoctor(@RequestBody AddDoctorRequest request) {
        adminService.addDoctor(
            request.getDoctor(),
            request.getUserCreds().getUsername(),
            request.getUserCreds().getPassword()
        );

        return ResponseEntity.ok().build();
    }

    @PostMapping("/patient")
    @SecurityRequirement(name = "Authorization")
    @Operation(summary = "Создать уз пациента")
    public ResponseEntity<?> addPatient(@RequestBody AddPatientRequest request) {
        var result = adminService.addPatient(
            request.getPatient(),
            request.getUserCreds().getUsername(),
            request.getUserCreds().getPassword()
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user")
    @Operation(summary = "Удалить уз")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> deleteUser(@RequestBody String username) {
        adminService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/patient/doctor")
    @Operation(summary = "Назначить пациенту врача")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> addDoctorToPatient(@RequestBody AddDoctorToPatientRequest addDoctorToPatientRequest) {
        adminService.addDoctorToPatient(addDoctorToPatientRequest.patientId(), addDoctorToPatientRequest.doctorId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user")
    @Operation(summary = "Изменить логин/пароль уз")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> changeCredentials(@RequestBody ChangeCredentialsRequest request) {
        adminService.changeUserCredentials(request.username(), request.newUsername(), request.newPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/patients")
    @Operation(summary = "Получить данные всех пациентов")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> getPatients() {
        return ResponseEntity.ok(adminService.getPatients());
    }

    @GetMapping("/doctors")
    @Operation(summary = "Получить данные всех врачей")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> getDoctor() {
        return ResponseEntity.ok(adminService.getDoctors());
    }

    @GetMapping("/user")
    @Operation(summary = "Получить всех пользователей")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(adminService.getUsernames());
    }
}
