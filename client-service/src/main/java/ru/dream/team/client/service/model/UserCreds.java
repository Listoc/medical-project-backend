package ru.dream.team.client.service.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreds {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
