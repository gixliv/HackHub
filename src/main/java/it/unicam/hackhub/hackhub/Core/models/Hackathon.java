package it.unicam.hackhub.hackhub.Core.models;

import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoHackathon stato;

    @Column(nullable = false)
    private int dimensioneMaxTeam;

    @Column(nullable = false)
    private int numMaxTeam;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> team;

}
