package ru.dream.team.client.service.model.remind;

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
