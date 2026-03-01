package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriTeam;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
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
        return repositoryMembriTeamJpa.findAll()
                .stream()
                .filter(m -> m.getUtente().getId().equals(idUtente))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MembroTeam removeMember(Long utenteId, Long teamId) {
        Optional<MembroTeam> membroOpt = repositoryMembriTeamJpa.findAll()
                .stream()
                .filter(m ->
                        m.getUtente().getId().equals(utenteId) &&
                                m.getTeam().getId().equals(teamId)
                )
                .findFirst();

        if (membroOpt.isPresent()) {
            MembroTeam membro = membroOpt.get();
            repositoryMembriTeamJpa.delete(membro);
            return membro;
        }

        return null;
    }

    @Override
    public List<MembroTeam> findAllByTeam(Team team) {
        return repositoryMembriTeamJpa.findAll()
                .stream()
                .filter(m -> m.getTeam().equals(team))
                .toList();
    }

    @Override
    public MembroTeam delete(MembroTeam membroTeam) {
        repositoryMembriTeamJpa.delete(membroTeam);
        return membroTeam;
    }

    @Override
    public Optional<MembroTeam> findByUtente(Utente utente) {
        return repositoryMembriTeamJpa.findByUtente(utente);
    }

    @Override
    public MembroTeam save(MembroTeam membroTeam) {
        return repositoryMembriTeamJpa.save(membroTeam);
    }
}