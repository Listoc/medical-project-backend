package ru.dream.team.client.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dream.team.client.service.db.enitity.MessageDto;
import ru.dream.team.client.service.model.message.request.AddMessageRequest;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(target = "id", ignore = true)
    MessageDto mapMessageRequestToMessageDto(AddMessageRequest request);
}
