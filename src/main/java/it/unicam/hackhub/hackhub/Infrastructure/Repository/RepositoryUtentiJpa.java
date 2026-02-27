package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryUtentiJpa extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);

    List<Utente> findAllByRole(Ruolo ruolo);
}
