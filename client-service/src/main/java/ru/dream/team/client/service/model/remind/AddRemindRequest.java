package ru.dream.team.client.service.model.remind;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.dream.team.client.service.controller.AdminController;
import ru.dream.team.client.service.db.enitity.PatientDto;

import java.time.OffsetTime;

@Data
public class AddRemindRequest {
    @NotNull
    @JsonView(AdminController.class)
    private Long patientId;
    @NotBlank
    @JsonView(AdminController.class)
    private String patientFirstName;
    @NotBlank
    @JsonView(AdminController.class)
    private String patientLastName;
    @JsonView(AdminController.class)
    private String patientMiddleName;
    @JsonView(AdminController.class)
    @Email
    private String patientEmail;
    @NotNull
    @JsonView(PatientDto.class)
    private OffsetTime time;
}
