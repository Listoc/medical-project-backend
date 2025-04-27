package ru.dream.team.remind.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dream.team.remind.service.db.dto.Patient;
import ru.dream.team.remind.service.db.dto.PatientRemind;
import ru.dream.team.remind.service.db.repository.PatientRepository;
import ru.dream.team.remind.service.db.repository.RemindRepository;
import ru.dream.team.remind.service.model.AddRemindRequest;
import ru.dream.team.remind.service.model.ChangeRemindRequest;
import ru.dream.team.remind.service.model.RemindResponse;

import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RemindService {
    private final RemindRepository remindRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public List<PatientRemind> getReminds(OffsetTime offsetTime) {
        return remindRepository
            .findAll()
            .stream()
            .filter(r ->
                r.getRemindTime().withOffsetSameInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.MINUTES)
                        .equals(offsetTime.truncatedTo(ChronoUnit.MINUTES).withOffsetSameInstant(ZoneOffset.UTC))
            )
            .toList();
    }

    @Transactional
    public RemindResponse addRemind(AddRemindRequest addRemindRequest) {
        var patient = patientRepository.findById(addRemindRequest.getPatientId())
            .orElse(
                new Patient(
                    addRemindRequest.getPatientId(),
                    addRemindRequest.getPatientFirstName(),
                    addRemindRequest.getPatientLastName(),
                    addRemindRequest.getPatientMiddleName(),
                    addRemindRequest.getPatientEmail()
                )
            );

        var remind = new PatientRemind();

        remind.setRemindTime(addRemindRequest.getTime());

        patient = patientRepository.saveAndFlush(patient);

        patient.addRemind(remind);

        remind = remindRepository.save(remind);

        return new RemindResponse(remind.getRemindId(), remind.getRemindTime());
    }

    @Transactional
    public void deleteRemind(Long id) {
        remindRepository.deleteById(id);
    }

    @Transactional
    public RemindResponse changeRemind(ChangeRemindRequest request) {
        var remind = remindRepository.findById(request.getRemindId()).orElseThrow();
        remind.setRemindTime(request.getRemindTime());
        remind = remindRepository.save(remind);
        return new RemindResponse(remind.getRemindId(), remind.getRemindTime());
    }

    @Transactional
    public List<RemindResponse> getPatientReminds(Long id) {
        var patient = patientRepository.findById(id).orElseThrow();

        return patient.getPatientReminds()
                .stream()
                .map(remind -> new RemindResponse(
                        remind.getRemindId(),
                        remind.getRemindTime()
                )
        ).toList();
    }
}
