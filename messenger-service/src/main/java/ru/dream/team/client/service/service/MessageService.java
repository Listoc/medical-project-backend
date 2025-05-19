package ru.dream.team.client.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dream.team.client.service.db.enitity.MessageDto;
import ru.dream.team.client.service.db.repository.MessageRepository;
import ru.dream.team.client.service.mapper.MessageMapper;
import ru.dream.team.client.service.model.message.request.AddMessageRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final EmailSender emailSender;

    @Transactional
    public MessageDto addMessage(AddMessageRequest request) {
        var dto = messageRepository.save(messageMapper.mapMessageRequestToMessageDto(request));

        emailSender.sendEmail(request.getReceiverEmail());

        return dto;
    }

    @Transactional
    public List<MessageDto> getMessages(Long patientId, Long doctorId) {
        return messageRepository.findAllByDoctorIdAndPatientId(doctorId, patientId);
    }
}
