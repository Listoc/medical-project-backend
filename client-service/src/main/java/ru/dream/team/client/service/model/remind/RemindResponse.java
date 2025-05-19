package ru.dream.team.client.service.model.remind;

import java.time.OffsetTime;

public record RemindResponse(
        Long remindId,
        OffsetTime time
) {
}
