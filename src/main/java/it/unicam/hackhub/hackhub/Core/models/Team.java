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

    @ManyToOne
    @JoinColumn(name = "creatore_id")
    private Utente creatore;


    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @SQLRestriction("ruolo= 'MEMBRO_TEAM' ")
    private List<Utente> membri;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    
}
