package ru.dream.team.client.service.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.dream.team.client.service.model.message.AddMessageRequest;
import ru.dream.team.client.service.model.message.MessageResponse;
import ru.dream.team.client.service.model.remind.AddRemindRequest;
import ru.dream.team.client.service.model.remind.ChangeRemindRequest;
import ru.dream.team.client.service.model.remind.RemindResponse;

import java.util.List;

@Service
public class RemindApiService {
    private final RestClient restClient = RestClient.create();

    public RemindResponse addRemind(AddRemindRequest request) {
        return restClient
            .post()
            .uri(
                UriComponentsBuilder.fromUriString("http://localhost:8081/remind").build().toUri()
            )
            .body(request)
            .retrieve()
            .body(RemindResponse.class);
    }

    public RemindResponse changeRemind(ChangeRemindRequest request) {
        return restClient
            .put()
            .uri(
                UriComponentsBuilder.fromUriString("http://localhost:8081/remind").build().toUri()
            )
            .body(request)
            .retrieve()
            .body(RemindResponse.class);
    }

    public void deleteRemind(long remindId) {
        restClient
            .delete()
            .uri(
                UriComponentsBuilder.fromUriString("http://localhost:8081/remind/" + remindId).build().toUri()
            )
            .retrieve()
            .toBodilessEntity();
    }

    public List<RemindResponse> getReminds(long patientId) {
        var uri = UriComponentsBuilder
            .fromUriString("http://localhost:8081/remind/" + patientId)
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
