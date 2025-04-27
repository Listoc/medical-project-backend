package ru.dream.team.remind.service.model;

import java.time.OffsetTime;

public record RemindResponse(
        Long remindId,
        OffsetTime time
) {
}
