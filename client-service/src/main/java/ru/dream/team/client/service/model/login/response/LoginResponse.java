package ru.dream.team.client.service.model.login.response;

import ru.dream.team.client.service.db.enitity.UserDto;

public record LoginResponse(
    UserDto.Role userRole
) {
}
