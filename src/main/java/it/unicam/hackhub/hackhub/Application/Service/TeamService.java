package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ITeamService;
import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import it.unicam.hackhub.hackhub.Presentation.Exeptions.MemberNotFoundInTeamException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService implements ITeamService {
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryUtenti repositoryUtenti;

    public TeamService(IRepositoryTeam repositoryTeam, IRepositoryUtenti repositoryUtenti) {
        this.repositoryTeam = repositoryTeam;
        this.repositoryUtenti = repositoryUtenti;
    }


    @Override
    public Team creaTeam(TeamRequest request) {
        Team team = new Team();
        Utente creatore = repositoryUtenti.findById(request.getCreatore()).orElseThrow(EntityNotFoundException::new);
        if (creatore.getRuolo() == Ruolo.CREATORE_TEAM) throw new RuntimeException("Sei già creatore di un team");
        if (repositoryTeam.findTeamByName(request.getNome()).isPresent()) {
            throw new EntityExistsException("Team esistente");
        }

        team.setCreatore(creatore);
        team.setDescrizione(request.getDescrizione());
        team.setNome(request.getNome());
        team.setNumeroMassimoComponenti(request.getNumeroMassimoComponenti());
        team= repositoryTeam.insertInto(team).orElseThrow(EntityNotFoundException::new);
        creatore.setRuolo(Ruolo.CREATORE_TEAM);
        creatore.setTeam(team);
        repositoryUtenti.updateUtente(creatore);
        return team;
    }

    @Override
    @Transactional
    public boolean eliminaTeam(Long id) {
        Team team = repositoryTeam.findTeamById(id).orElseThrow(EntityNotFoundException::new);

        //quando il creatore del team decide di eliminare il team, è necessario che a tutti i membri del team venga scollegato il team e che venga modificato il ruolo
        //in seguito verra effettuata la stessa operazione anche al creatore del team.
        if (team.getMembri() != null) {
            for (Utente membro : team.getMembri()) {
                membro.setTeam(null);
                membro.setRuolo(Ruolo.UTENTE_GENERICO);
                repositoryUtenti.updateUtente(membro);
            }
        }

        Utente creatore = team.getCreatore();
        if (creatore != null) {
            creatore.setTeam(null);
            creatore.setRuolo(Ruolo.UTENTE_GENERICO);
            repositoryUtenti.updateUtente(creatore);
        }
        repositoryTeam.eliminaTeam(team.getId());
        //viene effettuata l'operazione di ricerca per verificare sia effettivamente andata a buon fine l'eliminazione
        return repositoryTeam.findTeamById(team.getId()).isEmpty();
    }

    @Override
    public List<Utente> getMembriTeam(Long id) {
        Team team = repositoryTeam.findTeamById(id).orElseThrow(EntityNotFoundException::new);
        return team.getMembri();
    }

    @Override
    public Utente getCreatoreTeam(Long id) {
        Team team = repositoryTeam.findTeamById(id).orElseThrow(EntityNotFoundException::new);
        return team.getCreatore();
    }

    //Qualora il creatore del team decida di apportare delle modifiche al team viene effettuata la modifica di alcuni o tutti i campi.
    //in caso di modifica parziale, i campi non modificati restano invariati
    @Override
    public Team updateTeam(Long id, int numeroMassimoComponenti, String nome, String descrizione) {
        Team team = repositoryTeam.findTeamById(id).orElseThrow(EntityNotFoundException::new);
        if (nome != null && !nome.isBlank()) team.setNome(nome);
        if (descrizione != null && !descrizione.isBlank()) team.setDescrizione(descrizione);
        if (numeroMassimoComponenti > 0) team.setNumeroMassimoComponenti(numeroMassimoComponenti);
        return repositoryTeam.updateTeam(team).orElseThrow(RuntimeException::new);
    }

    //il metodo setMembro viene utilizzato qualora un utente accetta un invito ad unirsi ad un team
    //l'utente può partecipare solo ad un team alla volta.
    @Override
    public boolean setMembro(Long idUtente, Long idTeam) {
        Utente utente = repositoryUtenti.findById(idUtente).orElseThrow(EntityNotFoundException::new);
        Team team = repositoryTeam.findTeamById(idTeam).orElseThrow(EntityNotFoundException::new);
        if (utente.getTeam() == null) {
            utente.setTeam(team);
            repositoryUtenti.updateUtente(utente);
            return true;
        }
        return false;
    }

    @Override
    public Utente getMembroById(Long idUtente, Long idTeam) {
        Utente utente = repositoryUtenti.findById(idUtente).orElseThrow(EntityNotFoundException::new);
        if(repositoryTeam.checkMembroById(idUtente, idTeam)) return utente;
        throw new MemberNotFoundInTeamException();
    }

    @Override
    public Hackathon getHackathon(Long idTeam) {
        Team team  = repositoryTeam.findTeamById(idTeam).orElseThrow(EntityNotFoundException::new);
        return team.getHackathon();
    }

    //lista di tutti i team presenti nel sistema
    @Override
    public List<Team> getAllTeams() {
        return repositoryTeam.findAllTeam();
    }

    //eliminaMembro viene utiizzata qualora il creatore del team decide di espellere un membro dal suo team
    @Override
    public boolean eliminaMembro(Long idUtente, Long idTeam) {
        Utente utente = getMembroById(idUtente, idTeam);
        utente.setTeam(null);
        utente.setRuolo(Ruolo.UTENTE_GENERICO);
        repositoryUtenti.updateUtente(utente);
        return true;
    }

    //abbandonaTeam viene utilizzata qualora un membro del team decide di uscire dal team
    @Override
    public boolean abbandonaTeam(Long idUtente, Long idTeam) {
        Utente utente=repositoryUtenti.findById(idUtente).orElseThrow(EntityNotFoundException::new);
        Team team=repositoryTeam.findTeamById(idTeam).orElseThrow(EntityNotFoundException::new);

        if(utente.getTeam().getNome().equals(team.getNome())){
            eliminaMembro(idUtente,idTeam);
            repositoryTeam.updateTeam(team);
            return true;
        }
        return false;
    }

}
