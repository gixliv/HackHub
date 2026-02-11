package it.unicam.hackhub.hackhub.Application.DTO.Response;

import it.unicam.hackhub.hackhub.core.enums.StatoInvito;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvitoResponse {
    private final Long id;
    private final String descrizione;
    private final UtenteResponse mittente;
    private final UtenteResponse destinatario;
    private final StatoInvito stato;
}