package ru.dream.team.remind.service.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.dream.team.remind.service.db.dto.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
