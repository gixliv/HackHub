package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.core.models.Utente;

import java.util.Optional;

public interface IRepositoryUtenti{

    Optional<Utente> getUtenteById(Long id);
}
