package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriTeam;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryMembriTeamJpa;

public class RepositoryMembriTeamAdp implements IRepositoryMembriTeam {
    private final RepositoryMembriTeamJpa repositoryMembriTeamJpa;

    public RepositoryMembriTeamAdp(RepositoryMembriTeamJpa repositoryMembriTeamJpa) {
        this.repositoryMembriTeamJpa = repositoryMembriTeamJpa;
    }

}
