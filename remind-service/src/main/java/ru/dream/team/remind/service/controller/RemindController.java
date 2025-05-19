package ru.dream.team.remind.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dream.team.remind.service.model.AddRemindRequest;
import ru.dream.team.remind.service.model.ChangeRemindRequest;
import ru.dream.team.remind.service.model.RemindResponse;
import ru.dream.team.remind.service.service.RemindService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RemindController {
    private final RemindService remindService;

    @GetMapping("/remind/{id}")
    @Operation(summary = "Получить все уведомления пациента (по id пациента)")
    public List<RemindResponse> getReminds(@PathVariable Long id) {
        return remindService.getPatientReminds(id);
    }

    @PostMapping("/remind")
    @Operation(summary = "Добавить новое уведомление")
    public RemindResponse addRemind(@RequestBody @Valid AddRemindRequest addRemindRequest) {
        return remindService.addRemind(addRemindRequest);
    }

    @DeleteMapping("/remind/{id}")
    @Operation(summary = "Удалить уведомление по id уведомления")
    public void deleteRemind(@PathVariable Long id) {
        remindService.deleteRemind(id);
    }

    @PutMapping("/remind")
    @Operation(summary = "Изменить время уведомления")
    public RemindResponse changeRemind(@RequestBody @Valid ChangeRemindRequest remindRequest) {
        return remindService.changeRemind(remindRequest);
    }
}
