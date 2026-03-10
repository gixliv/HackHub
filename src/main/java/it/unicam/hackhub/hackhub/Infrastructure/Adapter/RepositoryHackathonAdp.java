package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryHackathonJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryHackathonAdp implements IRepositoryHackathon {
    private final RepositoryHackathonJpa repositoryHackathonJpa;

    public RepositoryHackathonAdp(RepositoryHackathonJpa repositoryHackathonJpa) {
        this.repositoryHackathonJpa = repositoryHackathonJpa;
    }


    @Override
    public Optional<Hackathon> findHackathonById(Long id) {
        return repositoryHackathonJpa.findById(id);
    }

    @Override
    public Optional<Hackathon> findHackathonByName(String name) {
        return repositoryHackathonJpa.findByNome(name);
    }

    @Override
    public Optional<Hackathon> updateHackathon(Hackathon hackathon) {
        return Optional.of(repositoryHackathonJpa.save(hackathon));
    }

    @Override
    public List<Hackathon> getAllHackathon() {
        return repositoryHackathonJpa.findAll();
    }
}
