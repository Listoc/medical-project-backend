package ru.dream.team.client.service.db.enitity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "message")
public class MessageDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long id;

    @Column(name = "message_text")
    private String text;

    @Column(name = "message_date")
    private OffsetDateTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "producer")
    private Role producer;

    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "doctor_id")
    private long doctorId;

    public enum Role {
        ROLE_PATIENT, ROLE_DOCTOR
    }
}
