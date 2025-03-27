package ru.dream.team.client.service.model;

import lombok.Data;
import ru.dream.team.client.service.db.enitity.Patient;
import ru.dream.team.client.service.db.enitity.User;

@Data
public class AddPatientRequest {
    private Patient patient;
    private UserCreds userCreds;
}
