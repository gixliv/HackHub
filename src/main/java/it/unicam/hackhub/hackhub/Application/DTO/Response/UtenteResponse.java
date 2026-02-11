package it.unicam.hackhub.hackhub.Application.DTO.Response;

import it.unicam.hackhub.hackhub.core.enums.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class UtenteResponse {
    private final Long id;
    private final String username;
    private final String nome;
    private final String cognome;
    private final char sesso;
    private final String email;
    private final String telefono;
    private final Ruolo ruolo;
    private final String iban;
    private final LocalDate dataNascita;
    private final List<InvitoResponse> invitiInviati;
    private final List<InvitoResponse> invitiRicevuti;
}