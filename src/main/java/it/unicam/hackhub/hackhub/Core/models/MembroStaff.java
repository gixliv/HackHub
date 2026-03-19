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
@DiscriminatorValue("MEMBRO_STAFF")
public class MembroStaff extends Utente{

    @Column(unique = true)
    private String codiceFiscale;

    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    @OneToMany(mappedBy = "organizzatore")
    private List<Hackathon> hackathonsOrganizzatore;

    @OneToMany(mappedBy = "giudice")
    private List<Hackathon> hackathonsGiudice;

    @ManyToMany(mappedBy = "mentori")
    private List<Hackathon> hackathonsMentore;

}
