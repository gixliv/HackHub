package it.unicam.hackhub.hackhub.Infrastructure.Repository;

import it.unicam.hackhub.hackhub.core.models.Invito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryInvitiJpa extends JpaRepository<Invito, Long> {
}
