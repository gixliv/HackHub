package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.enums.StatoInvito;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;
import java.util.Optional;

public interface IRepositoryInviti {
    Optional<Invito> insertInto(Invito invito);
    Optional<Invito> findInvitoById(Long id);
    Optional<Invito> changeState(Long id, StatoInvito stato);
    Optional<Invito> updateInvito(Invito invito);
    Optional<Invito> removeInvito(Long id);
}
