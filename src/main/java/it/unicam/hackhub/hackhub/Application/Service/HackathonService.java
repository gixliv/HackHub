package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriStaff;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IHackathonService;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HackathonService implements IHackathonService {

    private final IRepositoryHackathon repositoryHackathon;
    private final IRepositoryUtenti repositoryUtenti;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryMembriStaff repositoryMembriStaff;

    public HackathonService(IRepositoryHackathon repositoryHackathon, IRepositoryUtenti repositoryUtenti, IRepositoryTeam repositoryTeam, IRepositoryMembriStaff repositoryMembriStaff) {
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryUtenti = repositoryUtenti;
        this.repositoryTeam = repositoryTeam;
        this.repositoryMembriStaff= repositoryMembriStaff;
    }

    @Override
    public Hackathon iscriviTeamHackathon(Long idHackathon, Long idUtente) {
       Hackathon hackathon= repositoryHackathon.findHackathonById(idHackathon).orElseThrow (()-> new EntityNotFoundException("Hackathon non presente"));
       Utente utente=repositoryUtenti.findById(idUtente).orElseThrow(()-> new EntityNotFoundException("Utente non presente"));
       Team team=repositoryTeam.findTeamById(utente.getTeam().getId()).orElseThrow(()-> new EntityNotFoundException("Team non presente"));
       //verifica di tutti i dati necessari per l'iscrizione di un team ad un hackathon
       if(hackathon.getStato()!= StatoHackathon.IN_ISCRIZIONE) throw new RuntimeException("Hackathon non in fase di iscrizione!!");
       if(team.getNumeroMassimoComponenti() > hackathon.getDimensioneMaxTeam()) throw new RuntimeException("Il team ha un numero di componenti superiore a quello previsto");
       if(hackathon.getTeams().size() >= hackathon.getNumMaxTeam()) throw new RuntimeException("Impossibile iscriversi all'hackathon numero massimo di team raggiunti");
       if(LocalDate.now().isAfter(hackathon.getScadenzaIscrizioni())) throw new RuntimeException("Le iscrizioni sono chiuse");

       team.setHackathon(hackathon);
       repositoryTeam.updateTeam(team);
       hackathon.getTeams().add(team);
       repositoryHackathon.updateHackathon(hackathon);

       return hackathon;
    }

    @Override
    @Transactional
    public Hackathon disiscriviTeamHackathon(Long idHackathon, Long idUtente) {
        Hackathon hackathon= repositoryHackathon.findHackathonById(idHackathon).orElseThrow (()-> new EntityNotFoundException("Hackathon non presente"));
        Utente utente=repositoryUtenti.findById(idUtente).orElseThrow(()-> new EntityNotFoundException("Utente non presente"));
        Team team=repositoryTeam.findTeamById(utente.getTeam().getId()).orElseThrow(()-> new EntityNotFoundException("Team non presente"));

        if(hackathon.getStato()!= StatoHackathon.IN_ISCRIZIONE) throw new RuntimeException("Impossibile effettuare disiscrizione, l'hackathon è in "+ hackathon.getStato());

        hackathon.getTeams().remove(team);
        repositoryHackathon.updateHackathon(hackathon);
        team.setHackathon(null);
        repositoryTeam.updateTeam(team);

        return hackathon;
    }

    //lista di tutti i team iscritti ad uno specifico hackathon
    @Override
    public List<Team> getAllTeams(Long idHackathon) {
        Hackathon hackathon= repositoryHackathon.findHackathonById(idHackathon).orElseThrow (()-> new EntityNotFoundException("Hackathon non presente"));
        return hackathon.getTeams();

    }

    //lista di tutti gli hackathon presenti nel sistema
    @Override
    public List<Hackathon> getAllHackathon() {
        return repositoryHackathon.findAllHackathon();
    }

    @Override
    public Hackathon getHackathonByName(String name) {
        return repositoryHackathon.findHackathonByName(name).orElseThrow(() -> new EntityNotFoundException("Hackaton non presente") );
    }

    //lista di tutti gli hackathon a cui un membroStaff (giudice, mentore) viene assegnato come tale
    //nel caso dell'organizzatore, lista di tutti gli hackathon che organizza
    @Override
    public List<Hackathon> getAllMyHackathon(Long idUtente) {
        MembroStaff membro = (MembroStaff)repositoryUtenti.findById(idUtente).orElseThrow(() -> new EntityNotFoundException("Hackaton non presente"));

        if(membro.getRuolo().equals(Ruolo.GIUDICE)) return membro.getHackathonsGiudice();
        else if(membro.getRuolo().equals(Ruolo.MENTORE)) return membro.getHackathonsMentore();
        else if(membro.getRuolo().equals(Ruolo.ORGANIZZATORE)) return membro.getHackathonsOrganizzatore();

        throw new EntityNotFoundException();
    }

    public boolean deleteHackathon(Long idHackathon) {
        Hackathon hackathon=repositoryHackathon.findHackathonById(idHackathon).orElseThrow(()-> new EntityNotFoundException("Hackathon non presente"));
        List<Team> teams=hackathon.getTeams();
        for(Team team:teams){
            team.setHackathon(null);
            repositoryTeam.updateTeam(team);
        }
        List<MembroStaff> membri=hackathon.getMentori();
        membri.add(hackathon.getGiudice());
        for(MembroStaff membro:membri){
            membro.setHackathon(null);
            repositoryMembriStaff.updateMembroStaff(membro);
        }
        repositoryHackathon.deleteHackathonById(hackathon.getId());
        return repositoryHackathon.findHackathonById(hackathon.getId()).isEmpty();
    }
}
