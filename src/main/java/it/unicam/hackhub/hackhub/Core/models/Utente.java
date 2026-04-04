package it.unicam.hackhub.hackhub.Core.models;

import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "utenti")
//permette la creazione di due tabelle una padre utente e una figlia membroStaff.
//membroStaff eredita i campi di utente senza ridondanza di informazioni
//il recupero delle informazioni si ha con un join tra le tabelle tramite id(membroStaff ha solo id di utente)
@Inheritance(strategy = InheritanceType.JOINED)
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private char sesso;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(unique = true)
    private String telefono;

    //ruolo specifico per l'utente
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ruolo ruolo;

    @Column(unique = true)
    private String iban;

    @Column(nullable = false)
    private LocalDate dataNascita;

    //team a cui appartiene l'utente nel caso in cui sia membro o creatore del team
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    //lista di inviti che ha inviato nel caso in cui sia creatore del team
    @OneToMany(mappedBy = "mittente", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Invito> invitiInviati;

    //lista di inviti ricevuti
    @OneToMany(mappedBy = "destinatario", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Invito> invitiRicevuti;


}
