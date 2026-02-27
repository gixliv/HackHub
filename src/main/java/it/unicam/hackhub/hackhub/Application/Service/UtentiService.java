package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IUtentiService;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtentiService implements IUtentiService {

    private IRepositoryUtenti repositoryUtenti;

    @Override
    public Utente getUtenteById(Long id) {
        return repositoryUtenti.findById(id)
                .orElse(null);
    }

    @Override
    public Utente getUtenteByUsername(String username) {
        return repositoryUtenti.findByUsername(username)
                .orElse(null);
    }

    @Override
    public List<Utente> getUtentiByRuolo(Ruolo ruolo) {
        return repositoryUtenti.findAllByRole(ruolo);
    }
}
