package ru.dream.team.client.service.model.admin.request;

import jakarta.validation.Valid;
import lombok.Data;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.model.DoctorInfo;
import ru.dream.team.client.service.model.UserCreds;

@Data
public class AddDoctorRequest {
    @Valid
    private DoctorInfo doctorInfo;
    @Valid
    private UserCreds userCreds;
}
