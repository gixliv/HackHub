package it.unicam.hackhub.hackhub.Infrastructure.Adapter;


import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryUtentiJpa;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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
    public List<Utente> findAllByRuolo(Ruolo ruolo) {
        return repositoryUtentiJpa.findAllByRuolo(ruolo);
    }

    @Override
    public Optional<Utente> updateUtente(Utente utente) {
        return Optional.of(repositoryUtentiJpa.save(utente));
    }

    @Override
    public Optional<Utente> deleteUtente(Long id) {
        Utente utente = repositoryUtentiJpa.findById(id).orElseThrow(EntityNotFoundException::new);
        repositoryUtentiJpa.delete(utente);
        return Optional.of(utente);
    }

    @Override
    public Optional<Utente> insertInto(Utente utente) {
        return Optional.of(repositoryUtentiJpa.save(utente));
    }
}
