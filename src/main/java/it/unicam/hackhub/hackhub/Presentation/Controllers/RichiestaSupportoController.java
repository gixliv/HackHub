package it.unicam.hackhub.hackhub.Presentation.Controllers;

import it.unicam.hackhub.hackhub.Application.DTO.Request.RichiestaSupportoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.RichiestaSupportoResponse;
import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/richiestaSupporto")
public class RichiestaSupportoController {

    private final it.unicam.hackhub.hackhub.Application.Abstraction.Service.IRichiestaSupportoService richiestaSupportoService;

    public RichiestaSupportoController(it.unicam.hackhub.hackhub.Application.Abstraction.Service.IRichiestaSupportoService richiestaSupportoService) {
        this.richiestaSupportoService = richiestaSupportoService;
    }

    @PostMapping("/invia")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public RichiestaSupportoResponse inviaRichiestaSupporto(@RequestBody RichiestaSupportoRequest request) {
        if (request == null) throw new IllegalArgumentException();
        return richiestaSupportoService.inviaRichiestaSupporto(request);
    }

    @GetMapping("/all/{idHackathon}")
    @PreAuthorize("hasRole('MEMBRO_STAFF')")
    public List<RichiestaSupporto> getAllRichiesteSupporto(@PathVariable Long idHackathon) {
        if (idHackathon == null) throw new IllegalArgumentException();
        return richiestaSupportoService.getAllRichiesteSupporto(idHackathon);
    }

}
