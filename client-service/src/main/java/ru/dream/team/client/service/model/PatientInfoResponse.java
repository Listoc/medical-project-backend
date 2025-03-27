package ru.dream.team.client.service.model;

import ru.dream.team.client.service.db.enitity.Doctor;
import ru.dream.team.client.service.db.enitity.Patient;

public record PatientInfoResponse (
        Patient patient,
        Doctor doctor
) {
}
