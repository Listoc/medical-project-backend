package ru.dream.team.client.service.model;

public record AddDoctorToPatientRequest(
        long patientId,
        long doctorId
) {
}
