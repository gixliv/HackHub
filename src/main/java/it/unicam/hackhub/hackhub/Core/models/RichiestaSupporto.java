package it.unicam.hackhub.hackhub.Core.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "richiestaSupporto")
public class RichiestaSupporto {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Team team;

}
