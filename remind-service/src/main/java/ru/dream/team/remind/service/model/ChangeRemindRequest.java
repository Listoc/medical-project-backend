package ru.dream.team.remind.service.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetTime;

@Data
public class ChangeRemindRequest {
    @NotNull
    private Long remindId;
    @NotNull
    private OffsetTime remindTime;
}
