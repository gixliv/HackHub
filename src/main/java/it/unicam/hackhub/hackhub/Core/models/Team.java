package it.unicam.hackhub.hackhub.Core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @Column(unique = true)
    private String descrizione;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembroTeam> membri;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    
}
