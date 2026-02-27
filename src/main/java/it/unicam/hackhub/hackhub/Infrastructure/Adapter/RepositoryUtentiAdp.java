package it.unicam.hackhub.hackhub.Infrastructure.Adapter;


import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryUtentiJpa;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;
import java.util.Optional;

public class RepositoryUtentiAdp implements IRepositoryUtenti {
    private final RepositoryUtentiJpa repositoryUtentiJpa;

    public RepositoryUtentiAdp(RepositoryUtentiJpa repositoryUtentiJpa) {
        this.repositoryUtentiJpa = repositoryUtentiJpa;
    }


    @Override
    public Optional<Utente> findById(Long id) {
        return repositoryUtentiJpa.findById(id);

    }

    @Override
    public Optional<Utente> findByUsername(String username) {
        return repositoryUtentiJpa.findByUsername(username);
    }

    @Override
    public List<Utente> findAllByRole(Ruolo ruolo) {
        return repositoryUtentiJpa.findAllByRole(ruolo);
    }
}
