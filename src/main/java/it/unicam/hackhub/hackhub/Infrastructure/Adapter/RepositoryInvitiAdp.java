package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryInviti;
import it.unicam.hackhub.hackhub.Core.enums.StatoInvito;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryInvitiJpa;

import java.util.List;
import java.util.Optional;

public class RepositoryInvitiAdp implements IRepositoryInviti {

    @Override
    public Optional<Invito> insertInto(Invito invito) {
        return Optional.empty();
    }

    @Override
    public Optional<Invito> findInvitoById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Invito> changeState(Long id, StatoInvito stato) {
        return Optional.empty();
    }

    @Override
    public Optional<Invito> updateInvito(Invito invito) {
        return Optional.empty();
    }

    @Override
    public Optional<Invito> removeInvito(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Invito> getAllInviti(Utente utente) {
        return List.of();
    }
}
