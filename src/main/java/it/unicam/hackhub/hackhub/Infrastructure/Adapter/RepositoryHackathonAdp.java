package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryHackathonJpa;

public class RepositoryHackathonAdp implements IRepositoryHackathon {
    private final RepositoryHackathonJpa repositoryHackathonJpa;

    public RepositoryHackathonAdp(RepositoryHackathonJpa repositoryHackathonJpa) {
        this.repositoryHackathonJpa = repositoryHackathonJpa;
    }
}
