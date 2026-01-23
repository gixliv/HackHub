package it.unicam.hackhub.hackhub;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class Hackaton {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String nome;

    //TODO Cambiare tipo
    @Getter
    @Setter
    private String regolamento;

    @Getter
    @Setter
    private LocalDateTime scadenzaIscrizioni;

    @Getter
    @Setter
    private LocalDateTime dataInizio;

    @Getter
    @Setter
    private LocalDateTime dataFine;

    @Getter
    @Setter
    private String luogo;

    //TODO Cambiare tipo
    @Getter
    @Setter
    private String premio;

    @Getter
    @Setter
    private int dimensioneMassimaTeam;

    private Giudice giudice;

    private Mentore mentori[];

    //TODO Cambiare tipo
    @Getter
    @Setter
    private String argomento;

    @Getter
    @Setter
    private int numeroMassimoTeam;

    private Organizzatore organizzatore;

    private Team team[];

    //TODO Cambiare tipo
    @Getter
    @Setter
    private String stato;




}
