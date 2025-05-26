package ru.dream.team.client.service.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NNService {
    public String predict(byte[] imageData) throws Exception {
        var file = Files.createFile(Path.of("./model/image-" + UUID.randomUUID() + ".jpg"));
        try {
            Files.write(file, imageData);

            Process process = Runtime.getRuntime().exec(new String[]{"python", "./model/predict.py", file.toString()});

            // Чтение результата
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            String result = reader.lines().toList().getLast();

            // Чтение ошибок
            BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
            String error = errorReader.lines().collect(Collectors.joining("\n"));

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                return error;
            }

            return result;
        } finally {
            Files.deleteIfExists(file);
        }
    }
}
