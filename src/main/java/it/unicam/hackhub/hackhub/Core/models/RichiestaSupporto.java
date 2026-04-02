package it.unicam.hackhub.hackhub.Core.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name= "richiestaSupporto")
public class RichiestaSupporto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "mentore_id")
    @SQLRestriction("ruolo = 'MENTORE' ")
    private MembroStaff mentore;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Team team;

}
