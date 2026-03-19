package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IUtentiService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.UtenteMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.UtenteRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public boolean registrazione(UtenteRequest request) {
        UtenteMapper mapper = new UtenteMapper();
        Utente utente = mapper.toEntity(request);
        utente.setRuolo(Ruolo.UTENTE_GENERICO);
        repositoryUtenti.insertInto(utente);
        return repositoryUtenti.findById(utente.getId()).isPresent();
    }

}
