package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryTeamJpa;

public class RepositoryTeamAdp implements IRepositoryTeam {
    private final RepositoryTeamJpa repositoryTeamJpa;

    public RepositoryTeamAdp(RepositoryTeamJpa repositoryTeamJpa) {
        this.repositoryTeamJpa = repositoryTeamJpa;
    }

}
