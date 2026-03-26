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
//la colonna degli id utente prodotti dalla join con la tabella padre
@PrimaryKeyJoinColumn(name = "idUtente")
public class MembroStaff extends Utente{

    @Column(unique = true, nullable = false)
    private String codiceFiscale;

    //membro assegnato ad uno specifico hackathon a cui lui partecipa in questo slot di tempo
    //se non sta partecipando ad hackathon in corso il campo rimarrà nullo
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    //lista degli hackathon che ha organizzato
    @OneToMany(mappedBy = "organizzatore")
    private List<Hackathon> hackathonsOrganizzatore;

    //lista degli hackathon a cui è assegnato e a cui ha partecipato come giudice
    @OneToMany(mappedBy = "giudice")
    private List<Hackathon> hackathonsGiudice;

    //lista degli hackathon a cui è assegnato e a cui ha partecipato come mentore
    @ManyToMany(mappedBy = "mentori")
    private List<Hackathon> hackathonsMentore;

    //override di setRuolo per evitare che i ruoli assegnabili a membroStaff siano quelli utilizzati da utente
    @Override
    public void setRuolo(Ruolo ruolo) {
        if (ruolo != null && !ruolo.isRuoloStaff()) throw new IllegalArgumentException("Ruolo non valido");
        super.setRuolo(ruolo);
    }
}
