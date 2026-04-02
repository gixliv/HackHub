package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.Sottomissione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface RepositorySottomissioneJpa extends JpaRepository<Sottomissione, Long> {
    Optional<Sottomissione> findSottomissioneByTeamId(Long teamId);
    List<Sottomissione> findAllByHackathonId(Long hackathonId);


}
