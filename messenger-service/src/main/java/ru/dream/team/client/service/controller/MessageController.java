package ru.dream.team.client.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dream.team.client.service.db.enitity.MessageDto;
import ru.dream.team.client.service.model.message.request.AddMessageRequest;
import ru.dream.team.client.service.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseStatus(value = HttpStatus.OK)
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/message")
    @Operation(summary = "Добавить сообщение")
    public MessageDto addMessage(@RequestBody @Valid AddMessageRequest request) {
        return messageService.addMessage(
            request
        );
    }

    @GetMapping("/message")
    @Operation(summary = "Получить сообщения по id пациента и врача")
    public List<MessageDto> getMessages(@RequestParam Long patientId, @RequestParam Long doctorId) {
        return messageService.getMessages(patientId, doctorId);
    }
}
