package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;

import java.util.List;
import java.util.Optional;

public interface IRepositoryTeam {
    Optional<Team> findById(Long id);
    Optional<Team> insertInto(Team team);
    Optional<Team> updateTeam(Team team);
    Optional<Team> eliminaTeam(Long id);
    Optional<Team> removeHackathon(Long idTeam);
    Optional<Team> addHackathon(Long idTeam, Hackathon hackathon);
    List<Team> getAll();
}
