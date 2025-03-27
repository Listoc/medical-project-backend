package ru.dream.team.client.service.model;

public record ChangeCredentialsRequest(
        String username,
        String newUsername,
        String newPassword
) {
}
