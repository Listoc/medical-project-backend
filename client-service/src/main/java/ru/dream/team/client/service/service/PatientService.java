package ru.dream.team.client.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dream.team.client.service.db.enitity.PatientDto;
import ru.dream.team.client.service.db.repository.PatientRepository;
import ru.dream.team.client.service.db.repository.UserRepository;
import ru.dream.team.client.service.model.PatientInfo;
import ru.dream.team.client.service.model.admin.response.PatientInfoResponse;
import ru.dream.team.client.service.model.message.AddMessageRequest;
import ru.dream.team.client.service.model.message.MessageResponse;
import ru.dream.team.client.service.model.remind.AddRemindRequest;
import ru.dream.team.client.service.model.remind.ChangeRemindRequest;
import ru.dream.team.client.service.model.remind.RemindResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final RemindApiService remindApiService;
    private final MessageApiService messageApiService;

    @Transactional
    public PatientDto getPatientInfo(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found")).getPatientDto();
    }

    @Transactional
    public PatientDto changePatientInfo(PatientInfo patientInfo, String username) {
        var patientDto = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found")).getPatientDto();

        patientDto.setSex(patientInfo.getSex());
        patientDto.setBirthday(patientInfo.getBirthday());
        patientDto.setEmail(patientInfo.getEmail());
        patientDto.setFirstName(patientInfo.getFirstName());
        patientDto.setMiddleName(patientInfo.getMiddleName());
        patientDto.setLastName(patientInfo.getLastName());
        patientDto.setBirthday(patientInfo.getBirthday());
        patientDto.setAvatar(patientInfo.getAvatar());

        return patientRepository.save(patientDto);
    }

    @Transactional
    public List<RemindResponse> getReminds(String username) {
        var patient = userRepository.findByUsername(username).orElseThrow().getPatientDto();

        return remindApiService.getReminds(patient.getId());
    }

    @Transactional
    public RemindResponse addRemind(AddRemindRequest request, String username) {
        var patient = userRepository.findByUsername(username).orElseThrow().getPatientDto();
        request.setPatientId(patient.getId());
        request.setPatientFirstName(patient.getFirstName());
        request.setPatientLastName(patient.getLastName());
        request.setPatientMiddleName(patient.getMiddleName());
        request.setPatientEmail(patient.getEmail());
        request.setTime(request.getTime());

        return remindApiService.addRemind(request);
    }

    @Transactional
    public RemindResponse changeRemind(ChangeRemindRequest request) {
        return remindApiService.changeRemind(request);
    }

    @Transactional
    public void deleteRemind(long remindId) {
        remindApiService.deleteRemind(remindId);
    }

    @Transactional
    public MessageResponse addMessage(AddMessageRequest request, String username) {
        var patient = userRepository.findByUsername(username).orElseThrow().getPatientDto();
        request.setPatientId(patient.getId());
        request.setProducer(MessageResponse.Role.ROLE_PATIENT);
        request.setDoctorId(patient.getDoctor().getId());
        request.setReceiverEmail(patient.getDoctor().getEmail());

        return messageApiService.addMessage(request);
    }

    @Transactional
    public List<MessageResponse> getMessages(String username) {
        var patient = userRepository.findByUsername(username).orElseThrow().getPatientDto();

        return messageApiService.getMessages(patient.getId(), patient.getDoctor().getId());
    }
}
