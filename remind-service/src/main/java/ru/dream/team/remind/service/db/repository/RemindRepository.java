package ru.dream.team.remind.service.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dream.team.remind.service.db.dto.PatientRemind;

public interface RemindRepository extends JpaRepository<PatientRemind, Long> {
}
