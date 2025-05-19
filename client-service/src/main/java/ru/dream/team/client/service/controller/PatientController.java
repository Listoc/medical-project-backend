package ru.dream.team.client.service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.model.PatientInfo;
import ru.dream.team.client.service.model.message.AddMessageRequest;
import ru.dream.team.client.service.model.message.MessageResponse;
import ru.dream.team.client.service.model.remind.AddRemindRequest;
import ru.dream.team.client.service.model.remind.ChangeRemindRequest;
import ru.dream.team.client.service.model.remind.RemindResponse;
import ru.dream.team.client.service.service.JwtService;
import ru.dream.team.client.service.service.MessageApiService;
import ru.dream.team.client.service.service.PatientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@ResponseStatus(value = HttpStatus.OK)
public class PatientController {
    private final PatientService patientService;
    private final JwtService jwtService;

    @PostMapping("/patient/message")
    @Operation(summary = "Добавить сообщение")
    @JsonView(PatientDto.class)
    public MessageResponse addMessage(@RequestBody @Valid AddMessageRequest addMessageRequest, HttpServletRequest request) {
        return patientService.addMessage(addMessageRequest, jwtService.getAuthUser(request));
    }

    @GetMapping("/patient/message")
    @Operation(summary = "Получить сообщения по id пациента и врача")
    public List<MessageResponse> getMessages(HttpServletRequest request) {
        return patientService.getMessages(jwtService.getAuthUser(request));
    }

    @GetMapping("/patient")
    @JsonView(PatientDto.class)
    @Operation(summary = "Получить информацию о пациенте по jwt-токену")
    public PatientDto getPatientInfo(HttpServletRequest request) {
        return patientService.getPatientInfo(jwtService.getAuthUser(request));
    }

    @PutMapping("/patient")
    @JsonView(PatientDto.class)
    @Operation(summary = "Изменить данные пациента")
    public PatientDto changePatientInfo(@RequestBody PatientInfo patientInfo, HttpServletRequest request) {
        return patientService.changePatientInfo(patientInfo, jwtService.getAuthUser(request));
    }

    @PostMapping("/remind")
    @JsonView(PatientDto.class)
    @Operation(summary = "Добавить уведомление")
    public RemindResponse addRemind(@RequestBody AddRemindRequest addRemindRequest, HttpServletRequest request) {
        return patientService.addRemind(addRemindRequest, jwtService.getAuthUser(request));
    }

    @PutMapping("/remind")
    @Operation(summary = "Изменить уведомление")
    public RemindResponse changeRemind(@RequestBody ChangeRemindRequest changeRemindRequest) {
        return patientService.changeRemind(changeRemindRequest);
    }

    @DeleteMapping("/remind/{id}")
    @Operation(summary = "Удалить уведомление")
    public void deleteRemind(@PathVariable long id, HttpServletRequest request) {
        patientService.deleteRemind(id);
    }

    @GetMapping("/remind")
    @Operation(summary = "Получить уведомления пациента")
    public List<RemindResponse> getReminds(HttpServletRequest request) {
        return patientService.getReminds(jwtService.getAuthUser(request));
    }
}
