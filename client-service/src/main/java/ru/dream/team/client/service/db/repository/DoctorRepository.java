package ru.dream.team.client.service.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dream.team.client.service.db.enitity.DoctorDto;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorDto, Long> {
    Optional<DoctorDto> findByEmail(String email);
}
