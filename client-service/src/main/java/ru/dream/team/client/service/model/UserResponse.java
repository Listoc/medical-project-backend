package ru.dream.team.client.service.model;


import ru.dream.team.client.service.db.enitity.User;

public record UserResponse(
        String username,
        String firstName,
        String lastName,
        String middleName,
        String email,
        User.Role role,
        long id
) {
}
