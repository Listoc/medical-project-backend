package ru.dream.team.remind.service.db.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient_remind")
public class PatientRemind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long remindId;

    private OffsetTime remindTime;

    private OffsetDateTime lastSendAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    private Patient patient;
}
