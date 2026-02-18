package it.unicam.hackhub.hackhub.Core.models;

import it.unicam.hackhub.hackhub.Core.enums.StatoInvito;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invito")
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "mittente_id", nullable = false)
    private Utente mittente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Utente destinatario;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column
    private StatoInvito stato;

}
