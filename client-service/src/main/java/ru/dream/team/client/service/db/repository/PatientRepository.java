package ru.dream.team.client.service.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dream.team.client.service.db.enitity.PatientDto;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientDto, Long> {
    Optional<PatientDto> findByEmail(String email);
}
