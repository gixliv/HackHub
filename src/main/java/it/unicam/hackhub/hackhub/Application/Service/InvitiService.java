package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IMembriTeamService;
import it.unicam.hackhub.hackhub.Application.Service.MembriTeamService;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryInviti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IInvitiService;
import it.unicam.hackhub.hackhub.Application.DTO.Request.InvitoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Request.MembroTeamRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.enums.StatoInvito;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitiService implements IInvitiService {
    private final IRepositoryUtenti repositoryUtenti;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryMembriTeam repositoryMembriTeam;
    private final IRepositoryInviti repositoryInviti;
    private final IMembriTeamService membriTeamService;

    public InvitiService(IRepositoryUtenti repositoryUtenti, IRepositoryTeam repositoryTeam, IRepositoryMembriTeam repositoryMembriTeam, IRepositoryInviti repositoryInviti, IMembriTeamService membriTeamService) {
        this.repositoryUtenti = repositoryUtenti;
        this.repositoryTeam = repositoryTeam;
        this.repositoryMembriTeam = repositoryMembriTeam;
        this.repositoryInviti = repositoryInviti;
        this.membriTeamService = membriTeamService;
    }

    @Override
    public Invito invitaUtente(InvitoRequest request) {
        Utente utenteDest = repositoryUtenti.findById(request.getDestinatarioId()).orElseThrow(null);
        Utente utenteMitt = repositoryUtenti.findById(request.getMittenteId()).orElseThrow(null);
        MembroTeam membroTeam = repositoryMembriTeam.findMembroById(utenteMitt.getId()).orElseThrow(null);
        Team team= repositoryTeam.findById(request.getTeamId()).orElseThrow(null);
        Invito invito=new Invito();

        if(utenteMitt.getRuolo().equals(Ruolo.CREATORE_TEAM) && utenteDest.getRuolo().equals(Ruolo.UTENTE_GENERICO))
            if(membroTeam.getTeam().equals(request.getTeamId())){
                invito.setDescrizione(request.getDescrizione());
                invito.setTeam(team);
                invito.setDestinatario(utenteDest);
                invito.setMittente(utenteMitt);
                repositoryInviti.insertInto(invito);
            }
            else throw new RuntimeException("Il team inserito non corrisponde al team di appartenenza");
        else throw new RuntimeException("Unathorized");

        return invito;
    }

    @Override
    public List<Invito> getAllInviti(Long id) {
        Utente utente = repositoryUtenti.findById(id).orElseThrow(null);
        return utente.getInvitiRicevuti();
    }

    @Override
    public boolean accettaInvito(Long id) {
        Invito invito= repositoryInviti.findInvitoById(id).orElseThrow(null);

        Utente utente= invito.getDestinatario();
        if(utente.getRuolo().equals(Ruolo.MEMBRO_TEAM) || utente.getRuolo().equals(Ruolo.CREATORE_TEAM))
            throw new RuntimeException("Impossibile accettare l'invito, utente già appartenente ad un team.");

        invito.setStato(StatoInvito.ACCETTATO);
        utente.setRuolo(Ruolo.MEMBRO_TEAM);
        repositoryUtenti.updateUtente(utente);
        MembroTeamRequest request=new MembroTeamRequest();
        request.setUtenteId(utente.getId());
        request.setTeamId(invito.getTeam().getId());
        membriTeamService.addMembro(request);
        repositoryInviti.updateInvito(invito);

        return repositoryInviti.findInvitoById(invito.getId()).isPresent();
    }

    @Override
    public boolean rifiutaInvito(Long id) {
        Invito invito= repositoryInviti.findInvitoById(id).orElseThrow(null);
        invito.setStato(StatoInvito.RIFIUTATO);
        repositoryInviti.updateInvito(invito);

        return repositoryInviti.findInvitoById(invito.getId()).isPresent();
    }
}
