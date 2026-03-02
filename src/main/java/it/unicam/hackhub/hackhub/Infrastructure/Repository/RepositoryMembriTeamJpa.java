package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryMembriTeamJpa extends JpaRepository<MembroTeam, Long> {

    Optional<MembroTeam> findByUtente(Utente utente);

    Optional<MembroTeam> findByUtenteAndTeam(Utente utente, Team team);

    List<MembroTeam> findAllByTeam(Team team);

    Optional<MembroTeam> findByUtenteId(Long idUtente);

    Optional<MembroTeam> findByUtenteIdAndTeamId(Long utenteId, Long teamId);
}
