package ru.dream.team.client.service.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.dream.team.client.service.controller.AdminController;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;

import java.time.OffsetDateTime;

@Data
public class AddMessageRequest {
    @JsonView({PatientDto.class, DoctorDto.class})
    private String text;

    @JsonView({PatientDto.class, DoctorDto.class})
    private OffsetDateTime time;

    @JsonView({AdminController.class})
    @Enumerated(EnumType.STRING)
    private MessageResponse.Role producer;

    @JsonView(DoctorDto.class)
    private long patientId;

    @JsonView({AdminController.class})
    private long doctorId;

    @JsonView({AdminController.class})
    private String receiverEmail;
}
