package it.unicam.hackhub.hackhub.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "segnalazioni")
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(length = 1000, nullable = false)
    private String descrizione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentore_id", nullable = false)
    private MembroStaff mentore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_segnalato_id")
    private Team teamSegnalato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;


}
