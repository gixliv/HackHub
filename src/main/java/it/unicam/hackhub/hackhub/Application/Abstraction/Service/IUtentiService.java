package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;

public interface IUtentiService {
    Utente getUtenteById(Long id);

    Utente getUtenteByUsername(String username);

    List<Utente> getUtentiByRuolo(String ruolo);
}
