package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryInviti;
import it.unicam.hackhub.hackhub.Core.enums.StatoInvito;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryInvitiJpa;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryTeamJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryInvitiAdp implements IRepositoryInviti {
    private final RepositoryInvitiJpa repositoryInvitiJpa;

    public RepositoryInvitiAdp(RepositoryInvitiJpa repositoryInvitiJpa) {
        this.repositoryInvitiJpa = repositoryInvitiJpa;
    }

    @Override
    public Optional<Invito> insertInto(Invito invito) {
        return Optional.of(repositoryInvitiJpa.save(invito));
    }

    @Override
    public Optional<Invito> findInvitoById(Long id) {
        return repositoryInvitiJpa.findById(id);
    }


    @Override
    public Optional<Invito> updateInvito(Invito invito) {
        repositoryInvitiJpa.delete(invito);
        return Optional.of(invito);
    }

}
