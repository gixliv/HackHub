package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriTeam;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryMembriTeamJpa;

import java.util.List;
import java.util.Optional;

public class RepositoryMembriTeamAdp implements IRepositoryMembriTeam {

    private final RepositoryMembriTeamJpa repositoryMembriTeamJpa;

    public RepositoryMembriTeamAdp(RepositoryMembriTeamJpa repositoryMembriTeamJpa) {
        this.repositoryMembriTeamJpa = repositoryMembriTeamJpa;
    }

    @Override
    public Optional<MembroTeam> findById(Long id) {
        return repositoryMembriTeamJpa.findById(id);
    }

    @Override
    public MembroTeam findByUserId(Long idUtente) {
        return repositoryMembriTeamJpa
                .findByUtenteId(idUtente)
                .orElse(null);
    }

    @Override
    public MembroTeam removeMember(Long utenteId, Long teamId) {
        Optional<MembroTeam> membroOpt =
                repositoryMembriTeamJpa.findByUtenteIdAndTeamId(utenteId, teamId);

        if (membroOpt.isPresent()) {
            MembroTeam membro = membroOpt.get();
            repositoryMembriTeamJpa.delete(membro);
            return membro;
        }

        return null;
    }


    @Override
    public List<MembroTeam> findAllByTeam(Team team) {
        return repositoryMembriTeamJpa.findAllByTeam(team);
    }


    @Override
    public MembroTeam delete(MembroTeam membroTeam) {
        repositoryMembriTeamJpa.delete(membroTeam);
        return membroTeam;
    }

    @Override
    public MembroTeam save(MembroTeam membroTeam) {
        return repositoryMembriTeamJpa.save(membroTeam);
    }
}