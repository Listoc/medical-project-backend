package ru.dream.team.client.service.model.image;

import jakarta.persistence.*;
import lombok.*;

@Data
public class ImageDto {
    private long id;

    private byte[] data;

    private long patientId;

    private String diagnosis;

}
