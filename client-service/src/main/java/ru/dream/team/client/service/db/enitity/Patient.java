package ru.dream.team.client.service.db.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Patient {
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
    @JsonIgnore
    private Doctor doctor;

    public void addDoctor(Doctor doctor) {
        this.setDoctor(doctor);
        doctor.getPatients().add(this);
    }
}
