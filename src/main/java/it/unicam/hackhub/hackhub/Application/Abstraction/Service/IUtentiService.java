package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Core.models.Utente;

public interface IUtentiService {
    Utente getUtenteById(Long id);
}
