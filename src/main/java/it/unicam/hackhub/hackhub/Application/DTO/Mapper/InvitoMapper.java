package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.InvitoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.InvitoResponse;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

public class InvitoMapper {
    public Invito toEntity(InvitoRequest invitoRequest) {
        if (invitoRequest == null) return null;

        Invito invito = new Invito();
        invito.setDescrizione(invitoRequest.getDescrizione());

        // Crea un riferimento a Utente per il mittente
        Utente mittente = new Utente();
        mittente.setId(invitoRequest.getMittenteId());
        invito.setMittente(mittente);

        // Crea un riferimento a Utente per il destinatario
        Utente destinatario = new Utente();
        destinatario.setId(invitoRequest.getDestinatarioId());
        invito.setDestinatario(destinatario);

        return invito;
    }

    public InvitoResponse toResponse(Invito invito) {
        if (invito == null) return null;
        InvitoResponse invitoResponse = new InvitoResponse();
        invitoResponse.setDescrizione(invito.getDescrizione());
        invitoResponse.setMittenteId(invito.getMittente().getId());
        invitoResponse.setDestinatarioId(invito.getDestinatario().getId());
        invitoResponse.setStato(invito.getStato());
        return invitoResponse;
    }
}
