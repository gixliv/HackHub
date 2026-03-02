package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;
import java.util.Optional;

public interface IRepositoryUtenti {

    Optional<Utente> findById(Long id);
    Optional<Utente> findByUsername(String username);
    List<Utente> findAllByRuolo(Ruolo ruolo);
    Optional<Utente> updateUtente(Utente utente);
    Optional<Utente> deleteUtente(Long id);
    Optional<Utente> insertInto(Utente utente);
}
