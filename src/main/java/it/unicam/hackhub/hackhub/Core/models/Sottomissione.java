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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false, unique = true)
    private String linkRepository;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teams_id")
    private Team team;

}
