package it.unicam.hackhub.hackhub.Application.Abstraction.Service;
import it.unicam.hackhub.hackhub.Application.DTO.Request.RichiestaSupportoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.RichiestaSupportoResponse;
import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;

import java.util.List;

public interface IRichiestaSupportoService {

    RichiestaSupportoResponse inviaRichiestaSupporto(RichiestaSupportoRequest request);
    List<RichiestaSupporto> getAllRichiesteSupporto(Long idHackathon);

}
