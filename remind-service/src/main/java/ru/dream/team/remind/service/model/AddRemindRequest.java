package ru.dream.team.remind.service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetTime;

@Data
public class AddRemindRequest {
    @NotNull
    private Long patientId;
    @NotBlank
    private String patientFirstName;
    @NotBlank
    private String patientLastName;
    private String patientMiddleName;
    @Email
    private String patientEmail;
    @NotNull
    private OffsetTime time;
}
