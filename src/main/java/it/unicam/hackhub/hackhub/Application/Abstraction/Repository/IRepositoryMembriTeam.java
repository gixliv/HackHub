package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;
import java.util.Optional;

public interface IRepositoryMembriTeam {
    MembroTeam save(MembroTeam membroTeam);

    Optional<MembroTeam> findById(Long id);

    MembroTeam findByUserId(Long idUtente);

    MembroTeam removeMember(Long utenteId, Long teamId);

    List<MembroTeam> findAllByTeam(Team team);

    MembroTeam delete(MembroTeam membroTeam);

}
