package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryInviti;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryInvitiJpa;

public class RepositoryInvitiAdp implements IRepositoryInviti {
    private final RepositoryInvitiJpa repositoryInvitiJpa;

    public RepositoryInvitiAdp(RepositoryInvitiJpa repositoryInvitiJpa) {
        this.repositoryInvitiJpa = repositoryInvitiJpa;
    }

}
