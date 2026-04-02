package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositorySottomissione;
import it.unicam.hackhub.hackhub.Core.models.Sottomissione;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositorySottomissioneJpa;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryTeamJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class RepositorySottomissioneAdp implements IRepositorySottomissione {

    private final RepositorySottomissioneJpa repositorySottomissioneJpa;

    public RepositorySottomissioneAdp(RepositorySottomissioneJpa repositorySottomissioneJpa, RepositoryTeamJpa repositoryTeamJpa) {
        this.repositorySottomissioneJpa = repositorySottomissioneJpa;
    }

    @Override
    public Optional<Sottomissione> updateSottomissione(Sottomissione sottomissione) {
        return Optional.of(repositorySottomissioneJpa.save(sottomissione));
    }

    @Override
    public Optional<Sottomissione> findSottomissioneById(Long idSottomissione) {
        return repositorySottomissioneJpa.findById(idSottomissione);
    }

    @Override
    public Optional<Sottomissione> insertInto(Sottomissione sottomissione) {
        return Optional.of(repositorySottomissioneJpa.save(sottomissione));
    }

    @Override
    public Optional<Sottomissione> findSottomissioneByTeamId(Long teamId) {
        return repositorySottomissioneJpa.findSottomissioneByTeamId(teamId);
    }

    @Override
    public List<Sottomissione> findAllSottomissioni(Long idHackathon) {
        return repositorySottomissioneJpa.findAllByHackathonId(idHackathon);
    }
}
