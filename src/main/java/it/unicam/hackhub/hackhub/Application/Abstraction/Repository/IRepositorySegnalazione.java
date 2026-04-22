package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.Segnalazione;

import java.util.Optional;

public interface IRepositorySegnalazione {
    Optional<Segnalazione> deleteSegnalazione(Long idSegnalazione);
    Optional<Segnalazione> findSegnalazioneById(Long idSegnalazione);
    Optional<Segnalazione> insertInto(Segnalazione segnalazione);

}
