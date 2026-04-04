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

    //utente (creatore del team) mittente dell'invito
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mittente_id", nullable = false)
    private Utente mittente;

    //utente destinatario dell'invito
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Utente destinatario;

    //enum per identificare lo stato di accettazione dell'invito
    @Enumerated(EnumType.STRING)
    @Column
    private StatoInvito stato;

}
