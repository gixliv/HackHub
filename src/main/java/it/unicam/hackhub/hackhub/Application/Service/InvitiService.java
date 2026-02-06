package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IInvioInvitiService;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IRicezioneInvitiService;
import it.unicam.hackhub.hackhub.core.models.Invito;
import it.unicam.hackhub.hackhub.core.models.Utente;

import java.util.List;

public class InvitiService implements IInvioInvitiService, IRicezioneInvitiService {
    private final IRepositoryUtenti repositoryUtenti;

    public InvitiService(IRepositoryUtenti repositoryUtenti) {
        this.repositoryUtenti = repositoryUtenti;
    }

    @Override
    public Invito invitaUtente(Utente utente) {
        //TODO Qui va il DTO
        repositoryUtenti.getUtenteById(utente.getId());
        return null;
    }

    @Override
    public List<Invito> getAllInviti() {
        return List.of();
    }

    @Override
    public Invito accettaInvito() {
        return null;
    }
}
