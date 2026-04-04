package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryRichiestaSupporto;
import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryRichiestaSupportoJpa;
import org.springframework.stereotype.Repository;

import javax.imageio.event.IIOReadProgressListener;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryRichiestaSupportoAdp implements IRepositoryRichiestaSupporto {

    private final RepositoryRichiestaSupportoJpa repositoryRichiestaSupportoJpa;

    public RepositoryRichiestaSupportoAdp(RepositoryRichiestaSupportoJpa repositoryRichiestaSupportoJpa) {
        this.repositoryRichiestaSupportoJpa = repositoryRichiestaSupportoJpa;
    }

    @Override
    public Optional<RichiestaSupporto> updateRichiestaSupporto(RichiestaSupporto richiestaSupporto) {
        return Optional.of(repositoryRichiestaSupportoJpa.save(richiestaSupporto));
    }

    @Override
    public Optional<RichiestaSupporto> findRichiestaSupportoById(Long idRichiestaSupporto) {
        return repositoryRichiestaSupportoJpa.findById(idRichiestaSupporto);
    }

    @Override
    public Optional<RichiestaSupporto> insertInto(RichiestaSupporto richiestaSupporto) {
        return Optional.of(repositoryRichiestaSupportoJpa.save(richiestaSupporto));
    }

    @Override
    public List<RichiestaSupporto> findAllRichiesteSupporto(Long idHackathon) {
        return repositoryRichiestaSupportoJpa.findAllByHackathonId(idHackathon);
    }
}
