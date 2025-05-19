package ru.dream.team.client.service.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.dream.team.client.service.model.message.AddMessageRequest;
import ru.dream.team.client.service.model.message.MessageResponse;

import java.util.List;

@Service
public class MessageApiService {
    private final RestClient restClient = RestClient.create();

    public MessageResponse addMessage(AddMessageRequest request) {
        return restClient
            .post()
            .uri(
                UriComponentsBuilder.fromUriString("http://localhost:8084/message").build().toUri()
            )
            .body(request)
            .retrieve()
            .body(MessageResponse.class);
    }

    public List<MessageResponse> getMessages(long patientId, long doctorId) {
        var uri = UriComponentsBuilder
            .fromUriString("http://localhost:8084/message")
            .queryParam("patientId", patientId)
            .queryParam("doctorId", doctorId)
            .build()
            .toUri();

        return restClient
            .get()
            .uri(
                uri
            )
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
    }
}
