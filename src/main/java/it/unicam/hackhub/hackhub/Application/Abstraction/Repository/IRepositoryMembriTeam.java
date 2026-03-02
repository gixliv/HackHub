package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;

import java.util.List;
import java.util.Optional;

public interface IRepositoryMembriTeam {
    //vedi come abbiamo fatto noi
    MembroTeam save(MembroTeam membroTeam);

    Optional<MembroTeam> findMembroById(Long id);

    MembroTeam findUtenteById(Long idUtente);

    MembroTeam removeMembro(Long utenteId, Long teamId);

    //togliere, esiste già in teamService
    List<MembroTeam> findAllByTeam(Team team);

    //ridondante esiste removeMembro sopra, da rivedere comuunque
    MembroTeam delete(MembroTeam membroTeam);

}
