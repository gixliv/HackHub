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

    //Mentore che gestisce la richiesta di supporto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentore_id")
    @SQLRestriction("ruolo = 'MENTORE' ")
    private MembroStaff mentore;

    //Hackathon a cui è associata la richiesta di supporto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    //Team che ha inviato la richiesta di supporto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teams_id")
    private Team team;

}
