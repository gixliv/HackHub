package it.unicam.hackhub.hackhub.Core.models;

import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.SQLSelect;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static it.unicam.hackhub.hackhub.Core.enums.Ruolo.*;

@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numeroMassimoComponenti;

    @Column(unique = true)
    private String nome;

    @Column
    private String descrizione;

    //utente definito come creatore di uno specifico team
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatore_id")
    private Utente creatore;


    //lista degli utenti che fanno parte di uno specifico team come membroTeam
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @SQLRestriction("ruolo= 'MEMBRO_TEAM' ")
    private List<Utente> membri;

    //specifico hackathon a cui il team si è iscritto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    //Sottomissione appartenente a uno specifico team
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sottomissione_id")
    private Sottomissione sottomissione;

    //lista delle segnalazioni per uno specifico team
    @OneToMany(mappedBy = "teamSegnalato", fetch = FetchType.LAZY)
    private List<Segnalazione> segnalazioni;
    
}
