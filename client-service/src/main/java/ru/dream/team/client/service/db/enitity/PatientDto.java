package ru.dream.team.client.service.db.enitity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "patient")
@JsonView({PatientDto.class, DoctorDto.class})
public class PatientDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender sex;

    private LocalDate birthday;

    private byte[] avatar;

    private String diagnosis;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonView(PatientDto.class)
    private DoctorDto doctor;

    public void addDoctor(DoctorDto doctorDto) {
        this.setDoctor(doctorDto);
        doctorDto.getPatients().add(this);
    }
}
