package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IUtentiService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.UtenteMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.UtenteRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UtentiService implements IUtentiService {

    private final PasswordEncoder passwordEncoder;
    private final IRepositoryUtenti repositoryUtenti;

    public UtentiService(PasswordEncoder passwordEncoder, IRepositoryUtenti repositoryUtenti) {
        this.passwordEncoder = passwordEncoder;
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
        if (repositoryUtenti.findByUsername(request.getUsername()).isPresent()) throw new IllegalArgumentException("Utente già registrato");
        UtenteMapper mapper = new UtenteMapper();
        Utente utente = mapper.toEntity(request);
        utente.setRuolo(Ruolo.UTENTE_GENERICO);
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        repositoryUtenti.insertInto(utente);
        return repositoryUtenti.findById(utente.getId()).isPresent();
    }

    @Override
    public boolean login(String username, String password) {
        if(username==null || password==null) throw new IllegalArgumentException("inserire entrambe le credenziali");
        Utente utente = repositoryUtenti.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return passwordEncoder.matches(password, utente.getPassword());
    }

    @Override
    public Utente ModificaProfilo(Long id, String username, String nome, String cognome, char sesso, String email, String password, String telefono, String iban, LocalDate dataNascita) {
        Utente utente = repositoryUtenti.findById(id).orElseThrow(EntityNotFoundException::new);
        if (username != null && !username.isBlank()) utente.setUsername(username);
        if (nome != null && !nome.isBlank()) utente.setNome(nome);
        if (cognome != null && !cognome.isBlank()) utente.setCognome(cognome);
        if (sesso != '\u0000') utente.setSesso(sesso);
        if (email != null && !email.isBlank()) utente.setEmail(email);
        if (password != null && !password.isBlank()) utente.setPassword(passwordEncoder.encode(password));
        if (telefono != null && !telefono.isBlank()) utente.setTelefono(telefono);
        if (iban != null && !iban.isBlank()) utente.setIban(iban);
        if (dataNascita != null) utente.setDataNascita(dataNascita);
        return repositoryUtenti.updateUtente(utente).orElseThrow(RuntimeException::new);
    }

}
