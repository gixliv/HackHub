package it.unicam.hackhub.hackhub.Core.models;

import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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

    //enum per la definizione dello stato attuale dell'hackathon
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoHackathon stato;

    @Column(nullable = false)
    private int dimensioneMaxTeam;

    @Column(nullable = false)
    private int numMaxTeam;

    //lista dei team partecipanti ad uno specifico hackathon
    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Team> teams;

    //utente membro dello staff che ha organizzato uno specifico hackathon
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizzatore_id")
    private MembroStaff organizzatore;

    //utente membro dello staff a cui è stato assegnato il ruolo di giudice per uno specifico hackathon
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giudice_id")
    private MembroStaff giudice;

    //utente o utenti membri dello staff a cui è stato assegnato il ruolo di mentore per uno specifico hackathon
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "hackathon_mentori",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "mentore_id")
    )
    private List<MembroStaff> mentori;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sottomissione> sottomissioni;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private List<RichiestaSupporto> richiesteSupporto;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private List<Segnalazione> segnalazioni;


}
