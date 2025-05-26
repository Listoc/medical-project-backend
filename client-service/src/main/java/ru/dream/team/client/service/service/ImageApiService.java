package ru.dream.team.client.service.service;

import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import ru.dream.team.client.service.model.image.ImageDto;
import ru.dream.team.client.service.model.message.AddMessageRequest;
import ru.dream.team.client.service.model.message.MessageResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class ImageApiService {
    private final RestClient restClient = RestClient
        .builder()
        .requestFactory(customRequestFactory())
        .build();

    public ClientHttpRequestFactory customRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(20000);
        clientHttpRequestFactory.setConnectionRequestTimeout(20000);
        return clientHttpRequestFactory;
    }

    public void addImage(MultipartFile image, long patientId, String doctorEmail) {
        var uri = UriComponentsBuilder
            .fromUriString("http://localhost:8085/image")
            .queryParam("patientId", patientId)
            .queryParam("doctorEmail", doctorEmail)
            .build()
            .toUri();

        MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
        body.add("image", image.getResource());

        restClient
            .post()
            .uri(
                uri
            )
            .header("Content-Type", "multipart/form-data")
            .body(body)
            .retrieve()
            .toBodilessEntity();
    }

    public List<ImageDto> getImages(long patientId) {
        var uri = UriComponentsBuilder
            .fromUriString("http://localhost:8085/image")
            .queryParam("patientId", patientId)
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
