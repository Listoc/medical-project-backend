package ru.dream.team.client.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.dream.team.client.service.db.enitity.ImageDto;
import ru.dream.team.client.service.db.repository.ImageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final EmailSender emailSender;
    private final NNService nnService;

    @Transactional
    public ImageDto addImage(MultipartFile request, Long patientId, String doctorEmail) throws Exception {
        byte[] bytes = request.getBytes();
        var dto = new ImageDto();
        dto.setData(bytes);
        dto.setPatientId(patientId);
        dto.setDiagnosis(nnService.predict(bytes));
        dto = imageRepository.save(dto);

        emailSender.sendEmail(dto.getDiagnosis(), doctorEmail);

        return dto;
    }

    @Transactional
    public List<ImageDto> getImages(Long patientId) {
        return imageRepository.findAllByPatientId(patientId);
    }
}
