package ru.dream.team.client.service.model.admin.response;


import lombok.Builder;
import ru.dream.team.client.service.db.enitity.UserDto;

@Builder
public record UserResponse(
        String username,
        String firstName,
        String lastName,
        String middleName,
        String email,
        UserDto.Role role,
        long id
) {
}
