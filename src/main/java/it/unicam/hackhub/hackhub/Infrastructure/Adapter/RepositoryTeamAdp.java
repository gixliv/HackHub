package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryTeamJpa;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryUtentiJpa;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryTeamAdp implements IRepositoryTeam {
    private final RepositoryTeamJpa repositoryTeamJpa;
    private final RepositoryUtentiJpa repositoryUtentiJpa;

    public RepositoryTeamAdp(RepositoryTeamJpa repositoryTeamJpa, RepositoryUtentiJpa repositoryUtentiJpa) {
        this.repositoryTeamJpa = repositoryTeamJpa;
        this.repositoryUtentiJpa = repositoryUtentiJpa;
    }

    @Override
    public Optional<Team> findTeamById(Long id) {
        return repositoryTeamJpa.findById(id);
    }

    @Override
    public boolean checkMembroById(Long idUtente, Long idTeam) {
        Utente utente =repositoryUtentiJpa.findById(idUtente).orElseThrow(EntityNotFoundException::new);
        return utente.getTeam().getId().equals(idTeam);
    }

    @Override
    public Optional<Team> insertInto(Team team) {
        return Optional.of(repositoryTeamJpa.save(team));
    }
    @Override
    public Optional<Team> updateTeam(Team team) {
        return Optional.of(repositoryTeamJpa.save(team));
    }
    @Override
    public Optional<Team> eliminaTeam(Long id) {
        Team team = repositoryTeamJpa.findById(id).orElseThrow(EntityNotFoundException::new);
        repositoryTeamJpa.delete(team);
        return Optional.of(team);
    }
    @Override
    public List<Team> getAllTeam() {
        return repositoryTeamJpa.findAll();
    }

    @Override
    public Optional<Team> findTeamByName(String name) {
        return repositoryTeamJpa.findByName(name);
    }

}
