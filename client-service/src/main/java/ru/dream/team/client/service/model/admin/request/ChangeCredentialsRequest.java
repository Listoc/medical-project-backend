package ru.dream.team.client.service.model.admin.request;

import jakarta.validation.constraints.NotBlank;

public record ChangeCredentialsRequest(
    @NotBlank
    String username,
    String newUsername,
    String newPassword
) {
}
