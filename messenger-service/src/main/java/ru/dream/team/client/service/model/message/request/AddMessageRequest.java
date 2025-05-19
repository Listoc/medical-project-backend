package ru.dream.team.client.service.model.message.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.dream.team.client.service.db.enitity.MessageDto;

import java.time.OffsetDateTime;

@Data
public class AddMessageRequest {
    private String text;

    private OffsetDateTime time;

    @Enumerated(EnumType.STRING)
    private MessageDto.Role producer;

    private long patientId;

    private long doctorId;

    private String receiverEmail;
}
