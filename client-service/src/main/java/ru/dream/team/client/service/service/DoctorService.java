package ru.dream.team.client.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dream.team.client.service.db.enitity.DoctorDto;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.db.repository.DoctorRepository;
import ru.dream.team.client.service.db.repository.PatientRepository;
import ru.dream.team.client.service.db.repository.UserRepository;
import ru.dream.team.client.service.model.image.ImageDto;
import ru.dream.team.client.service.model.message.AddMessageRequest;
import ru.dream.team.client.service.model.message.MessageResponse;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final MessageApiService messageApiService;
    private final ImageApiService imageApiService;

    @Transactional
    public DoctorDto getDoctorInfo(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found")).getDoctorDto();
    }

    @Transactional
    public MessageResponse addMessage(AddMessageRequest request, String username) {
        var doctor = userRepository.findByUsername(username).orElseThrow().getDoctorDto();
        request.setProducer(MessageResponse.Role.ROLE_DOCTOR);
        request.setDoctorId(doctor.getId());
        request.setReceiverEmail(
            doctor.getPatients()
                .stream()
                .filter(patient -> patient.getId() == request.getPatientId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No patient with that id"))
                .getEmail()
        );

        return messageApiService.addMessage(request);
    }

    @Transactional
    public List<MessageResponse> getMessages(String username, Long patientId) {
        var doctor = userRepository.findByUsername(username).orElseThrow().getDoctorDto();

        if (doctor.getPatients().stream().filter(patient -> patient.getId() == patientId).findAny().isEmpty()) {
            return Collections.emptyList();
        }

        return messageApiService.getMessages(patientId, doctor.getId());
    }

    @Transactional
    public List<ImageDto> getImages(String username, Long patientId) {
        var doctor = userRepository.findByUsername(username).orElseThrow().getDoctorDto();

        if (doctor.getPatients().stream().filter(patient -> patient.getId() == patientId).findAny().isEmpty()) {
            return Collections.emptyList();
        }

        return imageApiService.getImages(patientId);
    }
}
