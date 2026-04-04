package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.SottomissioneRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.SottomissioneResponse;
import it.unicam.hackhub.hackhub.Core.models.Sottomissione;

import java.util.List;
import java.util.Optional;

public interface ISottomissioneService {

    Sottomissione inviaSottomissione(SottomissioneRequest request);
    Sottomissione aggiornaSottomissione(Long idSottomissione, SottomissioneRequest request);
    List<Sottomissione> getAllSottomissioni(Long idHackathon);
}
