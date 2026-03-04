package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;
import java.util.Optional;

public interface IRepositoryTeam {
    Optional<Team> findTeamById(Long idTeam);
    boolean checkMembroById(Long idUtente, Long idTeam);
    Optional<Team> insertInto(Team team);
    Optional<Team> updateTeam(Team team);
    Optional<Team> eliminaTeam(Long id);
    List<Team> getAllTeam();
}
