package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositorySegnalazione;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ISegnalazioneService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.SegnalazioneMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.SegnalazioneRequest;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Segnalazione;
import it.unicam.hackhub.hackhub.Core.models.Team;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegnalazioneService implements ISegnalazioneService {

    private final IRepositoryHackathon repositoryHackathon;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositorySegnalazione repositorySegnalazione;

    public SegnalazioneService(IRepositoryHackathon repositoryHackathon, IRepositoryTeam repositoryTeam, IRepositorySegnalazione repositorySegnalazione) {
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryTeam = repositoryTeam;
        this.repositorySegnalazione = repositorySegnalazione;
    }

    @Override
    public Segnalazione inviaSegnalazione(SegnalazioneRequest request) {
        if(request==null) return null;
        Hackathon hackathon=repositoryHackathon.findHackathonById(request.getIdHackathon()).orElseThrow(EntityNotFoundException::new);
        Team team=repositoryTeam.findTeamById(request.getIdTeam()).orElseThrow(EntityNotFoundException::new);
        SegnalazioneMapper mapper= new SegnalazioneMapper();
        Segnalazione segnalazione= mapper.toEntity(request);
        repositorySegnalazione.insertInto(segnalazione);

        hackathon.getSegnalazioni().add(segnalazione);
        team.getSegnalazioni().add(segnalazione);
        repositoryHackathon.updateHackathon(hackathon);
        repositoryTeam.updateTeam(team);


        return segnalazione;
    }

    @Override
    public List<Segnalazione> getAllSegnalazioni(Long idHackathon) {
        Hackathon hackathon=repositoryHackathon.findHackathonById(idHackathon).orElseThrow(EntityNotFoundException::new);
        return repositorySegnalazione.findAllSegnalazioni(hackathon.getId());
    }
}
