package ru.dream.team.client.service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.model.admin.request.AddDoctorRequest;
import ru.dream.team.client.service.model.admin.request.AddDoctorToPatientRequest;
import ru.dream.team.client.service.model.admin.request.AddPatientRequest;
import ru.dream.team.client.service.model.admin.request.ChangeCredentialsRequest;
import ru.dream.team.client.service.model.admin.response.LoginResponse;
import ru.dream.team.client.service.model.admin.response.UserResponse;
import ru.dream.team.client.service.service.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@ResponseStatus(value = HttpStatus.OK)
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/doctor")
    @Operation(summary = "Создать УЗ врача")
    public DoctorDto addDoctor(@RequestBody @Valid AddDoctorRequest request) {
        return adminService.addDoctor(
            request.getDoctorInfo(),
            request.getUserCreds()
        );
    }

    @PostMapping("/patient")
    @Operation(summary = "Создать УЗ пациента")
    public PatientDto addPatient(@RequestBody @Valid AddPatientRequest request) {

        return adminService.addPatient(
            request.getPatientInfo(),
            request.getUserCreds()
        );
    }

    @DeleteMapping("/user/{username}")
    @Operation(summary = "Удалить УЗ")
    public void deleteUser(@PathVariable String username) {
        adminService.deleteUser(username);
    }

    @PutMapping("/patient/doctor")
    @Operation(summary = "Назначить пациенту врача")
    public void addDoctorToPatient(@RequestBody @Valid AddDoctorToPatientRequest addDoctorToPatientRequest) {
        adminService.addDoctorToPatient(addDoctorToPatientRequest.patientId(), addDoctorToPatientRequest.doctorId());
    }

    @PutMapping("/user")
    @Operation(summary = "Изменить логин/пароль уз")
    public LoginResponse changeCredentials(@RequestBody @Valid ChangeCredentialsRequest request) {
        var newLogin = adminService.changeUserCredentials(request.username(), request.newUsername(), request.newPassword());
        return new LoginResponse(newLogin);
    }

    @GetMapping("/patients")
    @Operation(summary = "Получить данные всех пациентов")
    @JsonView(PatientDto.class)
    public List<PatientDto> getPatients() {
        return adminService.getPatients();
    }

    @GetMapping("/doctors")
    @Operation(summary = "Получить данные всех врачей")
    @JsonView(DoctorDto.class)
    public List<DoctorDto> getDoctor() {
        return adminService.getDoctors();
    }

    @GetMapping("/user")
    @Operation(summary = "Получить всех пользователей")
    public List<UserResponse> getUsers() {
        return adminService.getUsers();
    }
}
