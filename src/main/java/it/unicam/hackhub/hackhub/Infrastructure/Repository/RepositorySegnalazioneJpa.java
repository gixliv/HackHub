package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorySegnalazioneJpa extends JpaRepository<Segnalazione, Long> {
    List<Segnalazione> findAllByHackathonId(Long hackathonId);
    List<Segnalazione> findAllByTeamSegnalatoId(Long teamId);
}
