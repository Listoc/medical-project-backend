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
@Table(name = "image")
public class ImageDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long id;

    @Column(name = "image_data")
    private byte[] data;

    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "diagnosis")
    private String diagnosis;

}
