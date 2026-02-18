package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.UtenteRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.UtenteResponse;
import it.unicam.hackhub.hackhub.Core.models.Utente;

public class UtenteMapper {

    public Utente toEntity(UtenteRequest utenteRequest) {
        if (utenteRequest == null) return null;
        Utente utente = new Utente();
        utente.setUsername(utenteRequest.getUsername());
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setSesso(utenteRequest.getSesso());
        utente.setEmail(utenteRequest.getEmail());
        utente.setPassword(utenteRequest.getPassword());
        utente.setTelefono(utenteRequest.getTelefono());
        utente.setIban(utenteRequest.getIban());
        utente.setDataNascita(utenteRequest.getDataNascita());
        return utente;
    }

    public UtenteResponse toResponse(Utente utente) {
        if (utente == null) return null;
        UtenteResponse response = new UtenteResponse();
        response.setUsername(utente.getUsername());
        response.setNome(utente.getNome());
        response.setCognome(utente.getCognome());
        response.setSesso(utente.getSesso());
        response.setEmail(utente.getEmail());
        response.setTelefono(utente.getTelefono());
        response.setRuolo(utente.getRuolo());
        response.setIban(utente.getIban());
        response.setDataNascita(utente.getDataNascita());
        return response;
    }
}
