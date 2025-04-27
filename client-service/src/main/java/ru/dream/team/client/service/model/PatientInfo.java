package ru.dream.team.client.service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.dream.team.client.service.db.enitity.Gender;

import java.time.LocalDate;

@Data
public class PatientInfo {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middleName;
    @Email
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender sex;
    @NotNull
    private LocalDate birthday;
    private byte[] avatar;
    @NotBlank
    private String diagnosis;
}
