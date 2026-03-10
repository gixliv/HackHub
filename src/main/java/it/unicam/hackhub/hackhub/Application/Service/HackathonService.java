package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IHackathonService;
import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HackathonService implements IHackathonService {

    private final IRepositoryHackathon repositoryHackathon;
    private final IRepositoryUtenti repositoryUtenti;
    private final IRepositoryTeam repositoryTeam;

    public HackathonService(IRepositoryHackathon repositoryHackathon, IRepositoryUtenti repositoryUtenti, IRepositoryTeam repositoryTeam) {
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryUtenti = repositoryUtenti;
        this.repositoryTeam = repositoryTeam;
    }

    @Override
    public Hackathon iscriviTeamHackathon(Long idHackathon, Long idUtente) {
       Hackathon hackathon= repositoryHackathon.findHackathonById(idHackathon).orElseThrow (()-> new EntityNotFoundException("Hackathon non presente"));
       Utente utente=repositoryUtenti.findById(idUtente).orElseThrow(()-> new EntityNotFoundException("Utente non presente"));
       Team team=repositoryTeam.findTeamById(utente.getTeam().getId()).orElseThrow(()-> new EntityNotFoundException("Team non presente"));

       if(hackathon.getStato()!= StatoHackathon.IN_ISCRIZIONE) throw new RuntimeException("Hackathon non in fase di iscrizione!!");
       if(team.getNumeroMassimoComponenti() > hackathon.getDimensioneMaxTeam()) throw new RuntimeException("Il team ha un numero di componenti superiore a quello previsto");
       if(hackathon.getTeam().size() >= hackathon.getNumMaxTeam()) throw new RuntimeException("Impossibile iscriversi all'hackathon numero massimo di team raggiunti");
       if(LocalDate.now().isAfter(hackathon.getScadenzaIscrizioni())) throw new RuntimeException("Le iscrizioni sono chiuse");

       team.setHackathon(hackathon);
       repositoryTeam.updateTeam(team);
       hackathon.getTeam().add(team);
       repositoryHackathon.updateHackathon(hackathon);

       return hackathon;
    }

    @Override
    public Hackathon disiscriviTeamHackathon(Long idHackathon, Long idUtente) {
        Hackathon hackathon= repositoryHackathon.findHackathonById(idHackathon).orElseThrow (()-> new EntityNotFoundException("Hackathon non presente"));
        Utente utente=repositoryUtenti.findById(idUtente).orElseThrow(()-> new EntityNotFoundException("Utente non presente"));
        Team team=repositoryTeam.findTeamById(utente.getTeam().getId()).orElseThrow(()-> new EntityNotFoundException("Team non presente"));

        if(hackathon.getStato()!= StatoHackathon.IN_ISCRIZIONE) throw new RuntimeException("Impossibile effettuare disiscrizione, l'hackathon è in "+ hackathon.getStato());

        hackathon.getTeam().remove(team);
        repositoryHackathon.updateHackathon(hackathon);
        team.setHackathon(null);
        repositoryTeam.updateTeam(team);

        return hackathon;
    }

    @Override
    public List<Team> getAllTeams(Long idHackathon) {
        Hackathon hackathon= repositoryHackathon.findHackathonById(idHackathon).orElseThrow (()-> new EntityNotFoundException("Hackathon non presente"));
        return hackathon.getTeam();

    }
}
