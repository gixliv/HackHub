package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriStaff;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositorySegnalazione;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ISegnalazioneService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.SegnalazioneMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.SegnalazioneRequest;
import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
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
    private final IRepositoryMembriStaff repositoryMembriStaff;

    public SegnalazioneService(IRepositoryHackathon repositoryHackathon, IRepositoryTeam repositoryTeam, IRepositorySegnalazione repositorySegnalazione, IRepositoryMembriStaff membriStaff) {
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryTeam = repositoryTeam;
        this.repositorySegnalazione = repositorySegnalazione;
        this.repositoryMembriStaff = membriStaff;
    }

    //Creazione e invio di una nuova segnalazione
    @Override
    public Segnalazione inviaSegnalazione(SegnalazioneRequest request) {
        if(request==null) return null;
        //Verifica se team e hackathon siano presenti, se il team è iscritto all'hackathon.
        Hackathon hackathon=repositoryHackathon.findHackathonById(request.getIdHackathon()).orElseThrow(EntityNotFoundException::new);
        Team team=repositoryTeam.findTeamById(request.getIdTeam()).orElseThrow(EntityNotFoundException::new);
        if (!team.getHackathon().getId().equals(hackathon.getId())) throw new RuntimeException("Il team non è iscritto all'hackathon");
        if(hackathon.getStato()!= StatoHackathon.IN_CORSO) throw new RuntimeException("L'hackathon non è in corso");
        //verifica del mentore e della sua appartenenza all'hackathon
        MembroStaff mentore= repositoryMembriStaff.findMembroStaffById(request.getIdMentore()).orElseThrow(EntityNotFoundException::new);
        if(!mentore.getHackathon().getId().equals(hackathon.getId())) throw new RuntimeException("Mentore non appartenente all'hackathon");

        //creazione del mapper per l'entità
        SegnalazioneMapper mapper= new SegnalazioneMapper();
        Segnalazione segnalazione= mapper.toEntity(request);

        //Le entità team, mentore e hackathon vengono impostate manualmente per evitare deferenziazioni
        segnalazione.setMentore(mentore);
        segnalazione.setTeamSegnalato(team);
        segnalazione.setHackathon(hackathon);

        hackathon.getSegnalazioni().add(segnalazione);
        team.getSegnalazioni().add(segnalazione);
        repositoryHackathon.updateHackathon(hackathon);
        repositoryTeam.updateTeam(team);
        //se la segnalazione è stata aggiunta con successo viene restituita
        segnalazione=repositorySegnalazione.insertInto(segnalazione).orElseThrow();
        return segnalazione;
    }

    //Lista di tutte le segnalazioni di un hackathon
    @Override
    public List<Segnalazione> getAllSegnalazioni(Long idHackathon) {
        Hackathon hackathon=repositoryHackathon.findHackathonById(idHackathon).orElseThrow(EntityNotFoundException::new);
        return repositorySegnalazione.findAllSegnalazioni(hackathon.getId());
    }
}
