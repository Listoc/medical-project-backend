package ru.dream.team.client.service.model.admin.response;

import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;

public record PatientInfoResponse (
        PatientDto patientDto,
        DoctorDto doctorDto
) {
}
