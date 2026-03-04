package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IUtentiService;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtentiService implements IUtentiService {

    private final IRepositoryUtenti repositoryUtenti;

    public UtentiService(IRepositoryUtenti repositoryUtenti) {
        this.repositoryUtenti = repositoryUtenti;
    }

    @Override
    public Utente getUtenteById(Long id) {
        return repositoryUtenti.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Utente getUtenteByUsername(String username) {
        return repositoryUtenti.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Utente> getUtentiByRuolo(Ruolo ruolo) {
        return repositoryUtenti.findAllByRuolo(ruolo);
    }
}
