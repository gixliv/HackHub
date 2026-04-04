package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;
import java.util.List;
import java.util.Optional;

public interface IRepositoryRichiestaSupporto {

    Optional<RichiestaSupporto> updateRichiestaSupporto(RichiestaSupporto richiestaSupporto);
    Optional<RichiestaSupporto> findRichiestaSupportoById(Long idRichiestaSupporto);
    Optional<RichiestaSupporto> insertInto(RichiestaSupporto richiestaSupporto);
    List<RichiestaSupporto> findAllRichiesteSupporto(Long idHackathon);
}

