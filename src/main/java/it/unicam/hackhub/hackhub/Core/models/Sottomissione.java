package it.unicam.hackhub.hackhub.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@Entity
@Table(name= "sottomissione")
public class Sottomissione {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String titolo;

    @Column
    private String descrizione;

    @Column
    private String linkRepository;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    @OneToOne
    @JoinColumn(name = "teams_id")
    private Team team;

}
