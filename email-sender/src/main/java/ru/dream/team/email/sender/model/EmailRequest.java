package ru.dream.team.email.sender.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String title;
    private String message;
    private String receiver;
}
