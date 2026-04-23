package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.Segnalazione;

import java.util.List;
import java.util.Optional;

public interface IRepositorySegnalazione {
    Optional<Segnalazione> deleteSegnalazione(Long idSegnalazione);
    Optional<Segnalazione> findSegnalazioneById(Long idSegnalazione);
    Optional<Segnalazione> insertInto(Segnalazione segnalazione);
    List<Segnalazione> findAllSegnalazioni(Long idHackathon);
    List<Segnalazione> findAllSegnalazioniByTeamId(Long idTeam);
}
