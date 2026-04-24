package it.unicam.hackhub.hackhub.Presentation.Controllers;

import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IRichiestaSupportoService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.RichiestaSupportoMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.RichiestaSupportoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.RichiestaSupportoResponse;
import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/richiestaSupporto")
public class RichiestaSupportoController {

    private final IRichiestaSupportoService richiestaSupportoService;

    public RichiestaSupportoController(IRichiestaSupportoService richiestaSupportoService) {
        this.richiestaSupportoService = richiestaSupportoService;
    }

    //Invio di una nuova richiesta di supporto da parte di un membro del team
    @PostMapping("/invia")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public RichiestaSupportoResponse inviaRichiestaSupporto(@RequestBody RichiestaSupportoRequest request) {
        if (request == null) throw new IllegalArgumentException();
        RichiestaSupporto richiestaSupporto = richiestaSupportoService.inviaRichiestaSupporto(request);
        RichiestaSupportoMapper map = new RichiestaSupportoMapper();
        return map.toResponse(richiestaSupporto);
    }

    //Lista di tutte le richieste di supporto associate ad uno specifico hackathon
    @GetMapping("/all/{idHackathon}")
    @PreAuthorize("hasRole('MEMBRO_STAFF')")
    public List<RichiestaSupportoResponse> getAllRichiesteSupporto(@PathVariable Long idHackathon) {
        if (idHackathon == null) throw new IllegalArgumentException();
        List<RichiestaSupportoResponse> response = new java.util.ArrayList<>();
        RichiestaSupportoMapper map = new RichiestaSupportoMapper();
        for (RichiestaSupporto r : richiestaSupportoService.getAllRichiesteSupporto(idHackathon))
            response.add(map.toResponse(r));
        return response;
    }

}
