package ru.dream.team.client.service.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.dream.team.client.service.db.enitity.Gender;

import java.time.LocalDate;

@Data
@Valid
public class DoctorInfo {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    private String middleName;
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender sex;
    @NotNull
    private LocalDate birthday;

    private byte[] avatar;
    @NotBlank
    private String specialization;
    @NotBlank
    private String category;
}
