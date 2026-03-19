package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.UtenteRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.time.LocalDate;
import java.util.List;

public interface IUtentiService {
    Utente getUtenteById(Long id);

    Utente getUtenteByUsername(String username);

    List<Utente> getUtentiByRuolo(Ruolo ruolo);

    boolean registrazione(UtenteRequest utente);

    Utente ModificaProfilo(Long id, String username, String nome, String cognome, char sesso, String email, String password, String telefono, String iban, LocalDate dataNascita);

    boolean login(String username, String password);
}
