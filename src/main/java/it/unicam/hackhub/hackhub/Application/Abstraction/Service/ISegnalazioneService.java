package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.SegnalazioneRequest;
import it.unicam.hackhub.hackhub.Core.models.Segnalazione;

import java.util.List;

public interface ISegnalazioneService {

    Segnalazione inviaSegnalazione(SegnalazioneRequest request);
    List<Segnalazione> getAllSegnalazioni(Long idHackathon);
}
