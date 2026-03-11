package it.unicam.hackhub.hackhub.Presentation.Controllers;


import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IInvitiService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.InvitoMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.InvitoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.InvitoResponse;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/inviti")
public class InvitiController {

    private final IInvitiService invitiService;
    private final IRepositoryUtenti repositoryUtenti;

    public InvitiController(IInvitiService invitiService, IRepositoryUtenti repositoryUtenti) {
        this.invitiService = invitiService;
        this.repositoryUtenti = repositoryUtenti;
    }

    @PostMapping("/invita/{username}")
    @PreAuthorize("hasRole('CREATORE_TEAM')")
    public String invitaUtente(@RequestParam Long idMittente, @RequestParam String descrizione, @PathVariable String username){
        Utente utente= repositoryUtenti.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        InvitoRequest  request= new InvitoRequest();
        request.setDestinatarioId(utente.getId());
        request.setDescrizione(descrizione);
        request.setMittenteId(idMittente);
        invitiService.invitaUtente(request);
        return "Utente invitato";
    }

    @PostMapping("/invita")
    @PreAuthorize("hasRole('CREATORE_TEAM')")
    public String invitaUtente(@RequestBody InvitoRequest request){
        if(request == null) throw new IllegalArgumentException();
        invitiService.invitaUtente(request);
        return "Utente invitato";
    }

    @GetMapping("/{idUtente}")
    public List<InvitoResponse> getAllInviti(@PathVariable Long idUtente){
        if(idUtente == null) throw new IllegalArgumentException();
        List<Invito> inviti=invitiService.getAllInviti(idUtente);
        List<InvitoResponse> invitoResp= new ArrayList<>();
        InvitoMapper mapp= new InvitoMapper();
        for(Invito invito: inviti){
            invitoResp.add(mapp.toResponse(invito));
        }
        return invitoResp;
    }

    @PutMapping("/accetta/{idInvito}")
    @PreAuthorize("hasRole('UTENTE_GENERICO')")
    public String accettaInvito(@PathVariable Long idInvito){
        if(idInvito == null) throw new IllegalArgumentException();
        invitiService.accettaInvito(idInvito);
        return "Invito accettato";
    }

    @DeleteMapping("/rifiuta/{idInvito}")
    public String rifiutaInvito(@PathVariable Long idInvito){
        if(idInvito == null) throw new IllegalArgumentException();
        invitiService.rifiutaInvito(idInvito);
        return "Invito rifiutato";
    }
}
