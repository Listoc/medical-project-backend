package ru.dream.team.remind.service.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String title;
    private String message;
    private String receiver;
}
