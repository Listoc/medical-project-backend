package ru.dream.team.client.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.dream.team.client.service.db.enitity.ImageDto;
import ru.dream.team.client.service.service.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseStatus(value = HttpStatus.OK)
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/image")
    @Operation(summary = "Добавить снимок")
    public ImageDto addImage(@RequestParam("image") MultipartFile image, @RequestParam Long patientId, @RequestParam String doctorEmail) throws Exception {
        return imageService.addImage(
            image, patientId, doctorEmail
        );
    }

    @GetMapping("/image")
    @Operation(summary = "Получить снимки по id пациента")
    public List<ImageDto> getImages(@RequestParam Long patientId) {
        return imageService.getImages(patientId);
    }
}
