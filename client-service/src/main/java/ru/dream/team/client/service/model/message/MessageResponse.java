package ru.dream.team.client.service.model.message;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MessageResponse {
    private long id;

    private String text;

    private OffsetDateTime time;

    private Role producer;

    private long patientId;

    private long doctorId;

    public enum Role {
        ROLE_PATIENT, ROLE_DOCTOR
    }
}
