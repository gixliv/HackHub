package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryTeamJpa extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
}
