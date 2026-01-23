package it.unicam.hackhub.hackhub;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Utente {
    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String cognome;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private char genere;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String telefono;

    //TODO Cambiare tipo
    @Getter
    @Setter
    private String ruolo;

    @Getter
    @Setter
    private String iban;

    @Getter
    @Setter
    private LocalDate compleanno;

    public void creaTeam(){}

    public void unirsiteam() {}


}
