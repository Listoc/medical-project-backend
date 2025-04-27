package ru.dream.team.client.service.db.enitity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "doctor")
@JsonView({DoctorDto.class, PatientDto.class})
public class DoctorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender sex;

    private LocalDate birthday;

    private byte[] avatar;

    private String specialization;

    private String category;

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    @JsonView(DoctorDto.class)
    private List<PatientDto> patients = new ArrayList<>();
}
