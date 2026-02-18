package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IUtentiService;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.stereotype.Service;

@Service
public class UtentiService implements IUtentiService {

    private IRepositoryUtenti repositoryUtenti;

    @Override
    public Utente getUtenteById(Long id) {
        return repositoryUtenti.getUtenteById(id)
                .orElse(null);
    }
}
