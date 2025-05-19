package ru.dream.team.client.service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.model.message.AddMessageRequest;
import ru.dream.team.client.service.model.message.MessageResponse;
import ru.dream.team.client.service.service.DoctorService;
import ru.dream.team.client.service.service.JwtService;
import ru.dream.team.client.service.service.MessageApiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@ResponseStatus(value = HttpStatus.OK)
public class DoctorController {
    private final DoctorService doctorService;
    private final JwtService jwtService;

    @PostMapping("/doctor/message")
    @Operation(summary = "Добавить сообщение")
    @JsonView(DoctorDto.class)
    public MessageResponse addMessage(@RequestBody @Valid AddMessageRequest addMessageRequest, HttpServletRequest request) {
        return doctorService.addMessage(addMessageRequest, jwtService.getAuthUser(request));
    }

    @GetMapping("/doctor/message")
    @Operation(summary = "Получить сообщения по id пациента и врача")
    public List<MessageResponse> getMessages(@RequestParam Long patientId, HttpServletRequest request) {
        return doctorService.getMessages(jwtService.getAuthUser(request), patientId);
    }

    @GetMapping("/doctor")
    @JsonView(DoctorDto.class)
    @Operation(summary = "Получить информацию о враче по jwt-токену")
    public DoctorDto getDoctorInfo(HttpServletRequest request) {
        return doctorService.getDoctorInfo(jwtService.getAuthUser(request));
    }
}
