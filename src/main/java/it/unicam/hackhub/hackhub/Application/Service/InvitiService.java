package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IInvitiService;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;

public class InvitiService implements IInvitiService {
    private final IRepositoryUtenti repositoryUtenti;

    public InvitiService(IRepositoryUtenti repositoryUtenti) {
        this.repositoryUtenti = repositoryUtenti;
    }

    @Override
    public Invito invitaUtente(Utente utente) {
        //TODO Qui va il DTO
        repositoryUtenti.findById(utente.getId());
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
