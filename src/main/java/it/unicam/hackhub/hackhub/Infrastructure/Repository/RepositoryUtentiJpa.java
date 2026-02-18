package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUtentiJpa extends JpaRepository<Utente, Long> {
}
