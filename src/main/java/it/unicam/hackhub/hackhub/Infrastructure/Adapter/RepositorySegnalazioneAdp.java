package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositorySegnalazione;
import it.unicam.hackhub.hackhub.Core.models.Segnalazione;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositorySegnalazioneJpa;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositorySegnalazioneAdp implements IRepositorySegnalazione {
    private final RepositorySegnalazioneJpa repositorySegnalazioneJpa;

    public RepositorySegnalazioneAdp(RepositorySegnalazioneJpa repositorySegnalazioneJpa) {
        this.repositorySegnalazioneJpa = repositorySegnalazioneJpa;
    }

    @Override
    public Optional<Segnalazione> deleteSegnalazione(Long idSegnalazione) {
        Segnalazione segnalazione=repositorySegnalazioneJpa.findById(idSegnalazione).orElseThrow(EntityNotFoundException::new);
        repositorySegnalazioneJpa.delete(segnalazione);
        return Optional.of(segnalazione);
    }

    @Override
    public Optional<Segnalazione> findSegnalazioneById(Long idSegnalazione) {
        return repositorySegnalazioneJpa.findById(idSegnalazione);
    }

    @Override
    public Optional<Segnalazione> insertInto(Segnalazione segnalazione) {
        return Optional.of(repositorySegnalazioneJpa.save(segnalazione));
    }

    @Override
    public List<Segnalazione> findAllSegnalazioni(Long idHackathon) {
        return repositorySegnalazioneJpa.findAllByHackathonId(idHackathon);
    }

    @Override
    public List<Segnalazione> findAllSegnalazioniByTeamId(Long idTeam){
        return repositorySegnalazioneJpa.findAllByTeamSegnalatoId(idTeam);
    }
}
