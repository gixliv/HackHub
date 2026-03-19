package it.unicam.hackhub.hackhub.Application.DTO.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class UtenteRequest {

    private String username;
    private String nome;
    private String cognome;
    private char sesso;
    private String email;
    private String password;
    private String telefono;
    private String iban;
    private LocalDate dataNascita;


}
