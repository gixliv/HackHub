package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.InvitoRequest;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;
import java.util.Optional;

public interface IInvitiService {

    Invito invitaUtente(InvitoRequest invito);
    List<Invito> getAllInviti(Long id);
    Invito accettaInvito(Long id);
    Invito rifiutaInvito(Long id);}
