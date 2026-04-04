package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryRichiestaSupportoJpa extends JpaRepository<RichiestaSupporto, Long> {

    List<RichiestaSupporto> findAllByHackathonId(Long hackathonId);
}
