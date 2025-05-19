package ru.dream.team.client.service.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dream.team.client.service.db.enitity.MessageDto;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageDto, Long> {
   List<MessageDto> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId);
}
