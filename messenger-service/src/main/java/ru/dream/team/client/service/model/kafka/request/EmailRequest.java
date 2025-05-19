package ru.dream.team.client.service.model.kafka.request;

import lombok.Data;

@Data
public class EmailRequest {
    private String title;
    private String message;
    private String receiver;
}
