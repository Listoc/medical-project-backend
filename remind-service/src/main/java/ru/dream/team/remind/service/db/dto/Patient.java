package ru.dream.team.remind.service.db.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    private long patientId;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    public Patient(long patientId, String firstName, String lastName, String middleName, String email) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
    }

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<PatientRemind> patientReminds = new ArrayList<>();

    public void addRemind(PatientRemind patientRemind) {
        this.getPatientReminds().add(patientRemind);
        patientRemind.setPatient(this);
    }
}
