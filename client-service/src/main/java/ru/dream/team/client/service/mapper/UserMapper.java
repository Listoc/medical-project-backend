package ru.dream.team.client.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.db.enitity.UserDto;
import ru.dream.team.client.service.model.DoctorInfo;
import ru.dream.team.client.service.model.PatientInfo;
import ru.dream.team.client.service.model.admin.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patients", ignore = true)
    DoctorDto mapDoctorReqToDto(DoctorInfo doctorInfo);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    PatientDto mapPatientReqToPatientDto(PatientInfo patientInfo);
    default UserResponse mapUserDtoToUserResponse(UserDto userDto) {
        if (userDto.getDoctorDto() != null) {
            return new UserResponse(
                userDto.getUsername(),
                userDto.getDoctorDto().getFirstName(),
                userDto.getDoctorDto().getLastName(),
                userDto.getDoctorDto().getMiddleName(),
                userDto.getDoctorDto().getEmail(),
                userDto.getRole(),
                userDto.getDoctorDto().getId()
            );
        } else {
            return new UserResponse(
                userDto.getUsername(),
                userDto.getPatientDto().getFirstName(),
                userDto.getPatientDto().getLastName(),
                userDto.getPatientDto().getMiddleName(),
                userDto.getPatientDto().getEmail(),
                userDto.getRole(),
                userDto.getPatientDto().getId()
            );
        }
    }
}
