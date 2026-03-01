package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryHackathonJpa;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryTeamJpa;

import java.util.List;
import java.util.Optional;

public class RepositoryTeamAdp implements IRepositoryTeam {
    private final RepositoryTeamJpa repositoryTeamJpa;

    public RepositoryTeamAdp(RepositoryTeamJpa repositoryTeamJpa) {
        this.repositoryTeamJpa = repositoryTeamJpa;
    }

    @Override
    public Optional<Team> findById(Long id) {
        return repositoryTeamJpa.findById(id);
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
        Team team = repositoryTeamJpa.findById(id).orElse(null);
        repositoryTeamJpa.delete(team);
        return Optional.of(team);
    }
    @Override
    public Optional<Team> removeHackathonId(Long idTeam) {
        Team team = repositoryTeamJpa.findById(idTeam).orElse(null);
        assert team != null;
        team.setHackathon(null);
        repositoryTeamJpa.save(team);
        return Optional.of(team);
    }
    @Override
    public Optional<Team> addHackathonId(Long idTeam, Hackathon hackathon) {
        Team team = repositoryTeamJpa.findById(idTeam).orElse(null);
        assert team != null;
        team.setHackathon(hackathon);
        repositoryTeamJpa.save(team);
        return Optional.of(team);
    }

    @Override
    public List<Team> getAll() {
        return repositoryTeamJpa.findAll();
    }

}
