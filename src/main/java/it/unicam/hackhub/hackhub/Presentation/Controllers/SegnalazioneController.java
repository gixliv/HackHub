package it.unicam.hackhub.hackhub.Presentation.Controllers;

import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ISegnalazioneService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.SegnalazioneMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.SegnalazioneRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.SegnalazioneResponse;
import it.unicam.hackhub.hackhub.Core.models.Segnalazione;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/segnalazione")
public class SegnalazioneController {

    private final ISegnalazioneService segnalazioneService;

    public SegnalazioneController(ISegnalazioneService segnalazioneService) {
        this.segnalazioneService = segnalazioneService;
    }

    //Invio di una nuova segnalazione da parte di un mentore verso un team
    @PostMapping("/invia")
    @PreAuthorize("hasRole('MENTORE')")
    public SegnalazioneResponse inviaSegnalazione(@RequestBody SegnalazioneRequest segnalazioneRequest){
        if(segnalazioneRequest==null) throw new IllegalArgumentException("dati non validi");
        SegnalazioneMapper mapper= new SegnalazioneMapper();
        Segnalazione segnalazione=segnalazioneService.inviaSegnalazione(segnalazioneRequest);
        return mapper.toResponse(segnalazione);
    }

    //Lista di tutte le segnalazioni associate ad uno specifico hackathon
    @GetMapping("/all/{idHackathon}")
    public List<SegnalazioneResponse> getAllSegnalazioni(@PathVariable Long idHackathon){
        if(idHackathon==null) throw new IllegalArgumentException("dati non validi");
        SegnalazioneMapper mapper= new SegnalazioneMapper();
        List<SegnalazioneResponse> segnalazioniResponse= new ArrayList<>();
        List<Segnalazione> segnalazioni= segnalazioneService.getAllSegnalazioni(idHackathon);
        for(Segnalazione s: segnalazioni){
            segnalazioniResponse.add(mapper.toResponse(s));
        }
        return segnalazioniResponse;
    }
}
