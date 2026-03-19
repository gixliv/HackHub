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
@DiscriminatorColumn(name = "ruolo_utente", discriminatorType = DiscriminatorType.STRING)
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

    //TODO salvare la password criptata
    private String password;

    @Column(unique = true)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ruolo ruolo;

    @Column(unique = true)
    private String iban;

    @Column(nullable = false)
    private LocalDate dataNascita;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @OneToMany(mappedBy = "mittente", orphanRemoval = true)
    private List<Invito> invitiInviati;

    @OneToMany(mappedBy = "destinatario", orphanRemoval = true)
    private List<Invito> invitiRicevuti;


}
