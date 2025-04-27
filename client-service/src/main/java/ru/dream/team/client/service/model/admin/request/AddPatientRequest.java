package ru.dream.team.client.service.model.admin.request;

import jakarta.validation.Valid;
import lombok.Data;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.model.PatientInfo;
import ru.dream.team.client.service.model.UserCreds;

@Data
public class AddPatientRequest {
    @Valid
    private PatientInfo patientInfo;
    @Valid
    private UserCreds userCreds;
}
