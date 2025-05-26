package ru.dream.team.client.service.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dream.team.client.service.db.enitity.ImageDto;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageDto, Long> {
   List<ImageDto> findAllByPatientId(Long patientId);
}
