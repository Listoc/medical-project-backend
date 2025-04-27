package ru.dream.team.client.service.model.admin.request;

import jakarta.validation.constraints.NotNull;

public record AddDoctorToPatientRequest(
    @NotNull
    Long patientId,
    @NotNull
    Long doctorId
) {
}
