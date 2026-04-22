package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorySegnalazioneJpa extends JpaRepository<Segnalazione, Long> {
}
