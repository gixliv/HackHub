package it.unicam.hackhub.hackhub.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

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

    //Mentore(MembroStaff) che ha inviato la segnalazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentore_id", nullable = false)
    @SQLRestriction("ruolo = 'MENTORE' ")
    private MembroStaff mentore;

    //Team segnalato dal mentore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_segnalato_id")
    private Team teamSegnalato;

    //Hackathon a cui è associata la segnalazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;


}
