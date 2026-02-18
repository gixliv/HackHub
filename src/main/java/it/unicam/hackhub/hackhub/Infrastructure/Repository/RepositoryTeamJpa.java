package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTeamJpa extends JpaRepository<Team, Long> {
}
