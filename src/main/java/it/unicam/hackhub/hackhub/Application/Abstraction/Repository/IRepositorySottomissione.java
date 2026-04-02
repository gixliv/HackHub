package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Application.DTO.Request.SottomissioneRequest;
import it.unicam.hackhub.hackhub.Core.models.Sottomissione;

import java.util.List;
import java.util.Optional;

public interface IRepositorySottomissione {

    Optional<Sottomissione> updateSottomissione(Sottomissione sottomissione);
    Optional<Sottomissione> findSottomissioneById(Long idSottomissione);
    Optional<Sottomissione> insertInto(Sottomissione sottomissione);
    Optional<Sottomissione> findSottomissioneByTeamId(Long teamId);
    List<Sottomissione> findAllSottomissioni(Long idHackathon);

}
