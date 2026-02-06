package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.core.models.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryHackathonJpa extends JpaRepository<Hackathon, Long> {
}
