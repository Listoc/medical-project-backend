package ru.dream.team.client.service.model;

import lombok.Data;
import ru.dream.team.client.service.db.enitity.Doctor;

@Data
public class AddDoctorRequest {
    private Doctor doctor;
    private UserCreds userCreds;
}
