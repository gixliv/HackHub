package it.unicam.hackhub.hackhub.Application.DTO.Response;

import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UtenteResponse {
    private String username;
    private String nome;
    private String cognome;
    private char sesso;
    private String email;
    private String telefono;
    private Ruolo ruolo;
    private String iban;
    private LocalDate dataNascita;
    private String nomeTeam;
}