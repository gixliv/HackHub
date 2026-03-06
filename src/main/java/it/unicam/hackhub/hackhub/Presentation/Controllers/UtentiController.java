package it.unicam.hackhub.hackhub.Presentation.Controllers;

import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IUtentiService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.UtenteMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.UtenteRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.UtenteResponse;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/utenti")
public class UtentiController {
    private final IUtentiService utentiService;

    public UtentiController(IUtentiService utentiService) {this.utentiService = utentiService;}

    @GetMapping("/{idUtente}")
    public UtenteResponse getUtenteById(@PathVariable Long idUtente) {
        Utente utente = utentiService.getUtenteById(idUtente);
        UtenteMapper utenteMapper = new UtenteMapper();
        return utenteMapper.toResponse(utente);
    }

    @GetMapping("/username/{username}")
    public UtenteResponse getUtenteByUsername(@PathVariable String username) {
        Utente utente = utentiService.getUtenteByUsername(username);
        UtenteMapper utenteMapper = new UtenteMapper();
        return utenteMapper.toResponse(utente);
    }

    @GetMapping("/ruolo/{ruolo}")
    public List<UtenteResponse> getUtentiByRuolo(@PathVariable Ruolo ruolo){
        List<Utente> utenti = utentiService.getUtentiByRuolo(ruolo);
        UtenteMapper utenteMapper = new UtenteMapper();
        List<UtenteResponse> utenteResponse = new ArrayList<>();
        for (Utente utente : utenti) {
            utenteResponse.add(utenteMapper.toResponse(utente));
        }
        return utenteResponse;
    }
}
