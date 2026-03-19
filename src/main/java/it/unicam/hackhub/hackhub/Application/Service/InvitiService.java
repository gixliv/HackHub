package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryInviti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IInvitiService;
import it.unicam.hackhub.hackhub.Application.DTO.Request.InvitoRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.enums.StatoInvito;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitiService implements IInvitiService {
    private final IRepositoryUtenti repositoryUtenti;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryInviti repositoryInviti;
    private final TeamService teamService;

    public InvitiService(IRepositoryUtenti repositoryUtenti, IRepositoryTeam repositoryTeam, IRepositoryInviti repositoryInviti, TeamService teamService) {
        this.repositoryUtenti = repositoryUtenti;
        this.repositoryTeam = repositoryTeam;
        this.repositoryInviti = repositoryInviti;
        this.teamService = teamService;
    }

    @Override
    public Invito invitaUtente(InvitoRequest request) {
        if(request.getDescrizione()==null) throw new IllegalArgumentException("descrizione non inserita");
        Utente utenteDest = repositoryUtenti.findById(request.getDestinatarioId()).orElseThrow(EntityNotFoundException::new);
        Utente utenteMitt = repositoryUtenti.findById(request.getMittenteId()).orElseThrow(EntityNotFoundException::new);
        if(repositoryTeam.findTeamById(utenteMitt.getTeam().getId()).isEmpty() || utenteMitt.getTeam()==null) throw new EntityNotFoundException("Team non trovato o nullo");
        Invito invito = new Invito();

        if (utenteMitt.getRuolo().equals(Ruolo.CREATORE_TEAM) && utenteDest.getRuolo().equals(Ruolo.UTENTE_GENERICO)){
                invito.setDescrizione(request.getDescrizione());
                invito.setDestinatario(utenteDest);
                invito.setMittente(utenteMitt);
                invito.setStato(StatoInvito.PENDENTE);
                repositoryInviti.insertInto(invito);
            } else throw new RuntimeException("Unathorized");

        return invito;
    }

    @Override
    public List<Invito> getAllInviti(Long id) {
        Utente utente = repositoryUtenti.findById(id).orElseThrow(EntityNotFoundException::new);
        return utente.getInvitiRicevuti();
    }

    @Override
    public boolean accettaInvito(Long id) {
        Invito invito = repositoryInviti.findInvitoById(id).orElseThrow(EntityNotFoundException::new);

        Utente utente = invito.getDestinatario();
        if (utente.getRuolo().equals(Ruolo.MEMBRO_TEAM) || utente.getRuolo().equals(Ruolo.CREATORE_TEAM))
            throw new RuntimeException("Impossibile accettare l'invito, utente già appartenente ad un team.");

        int numMembri=invito.getMittente().getTeam().getMembri().size();
        if(numMembri >= invito.getMittente().getTeam().getNumeroMassimoComponenti() )
            throw new RuntimeException("Impossibile accettare l'invito, numero massimo di componenti raggiunto.");

        invito.setStato(StatoInvito.ACCETTATO);
        utente.setRuolo(Ruolo.MEMBRO_TEAM);
        repositoryUtenti.updateUtente(utente);
        if (!teamService.setMembro(utente.getId(), invito.getMittente().getTeam().getId())) {
            throw new RuntimeException("setMembro non andato a buon fine");
        }

        repositoryInviti.updateInvito(invito);
        return repositoryInviti.findInvitoById(invito.getId()).isEmpty();
    }

    @Override
    public boolean rifiutaInvito(Long id) {
        Invito invito = repositoryInviti.findInvitoById(id).orElseThrow(EntityNotFoundException::new);
        invito.setStato(StatoInvito.RIFIUTATO);
        repositoryInviti.updateInvito(invito);

        return repositoryInviti.findInvitoById(invito.getId()).isEmpty();
    }
}
