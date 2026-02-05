package it.unicam.hackhub.hackhub.core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hackathons")
public class Hackathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 1000)
    private String regolamento;
    
    @Column(nullable = false)
    private LocalDate scadenzaIscrizioni;

    @Column(nullable = false)
    private LocalDate dataInizio;

    @Column(nullable = false)
    private LocalDate dataFine;

    @Column(nullable = false)
    private String luogo;

    //TODO membri dello staff, stato

    @Column(nullable = false)
    private int dimensioneMaxTeam;


}
