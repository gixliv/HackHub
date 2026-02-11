package it.unicam.hackhub.hackhub.Application.DTO.Response;

import it.unicam.hackhub.hackhub.core.enums.StatoInvito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class InvitoResponse {
    private String descrizione;
    private UtenteResponse mittenteId;
    private UtenteResponse destinatarioId;
    private StatoInvito stato;
}