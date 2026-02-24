package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;
import java.util.Optional;

public interface IRepositoryUtenti{

    Optional<Utente> findById(Long id);

    Optional<Utente> findByUsername(String username);

    List<Utente> findAllByRuolo(String ruolo);
}
