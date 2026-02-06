package it.unicam.hackhub.hackhub.Infrastructure.Adapter;


import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryUtentiJpa;
import it.unicam.hackhub.hackhub.core.models.Utente;

import java.util.Optional;

public class RepositoryUtentiAdp implements IRepositoryUtenti {
    private final RepositoryUtentiJpa repositoryUtentiJpa;

    public RepositoryUtentiAdp(RepositoryUtentiJpa repositoryUtentiJpa) {
        this.repositoryUtentiJpa = repositoryUtentiJpa;
    }


    @Override
    public Optional<Utente> getUtenteById(Long id) {
        return repositoryUtentiJpa.findById(id);

    }

}
