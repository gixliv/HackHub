package it.unicam.hackhub.hackhub.Presentation.Controllers;

import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ISottomissioneService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.SottomissioneMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.SottomissioneRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.SottomissioneResponse;
import it.unicam.hackhub.hackhub.Core.models.Sottomissione;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/sottomissione")
public class SottomissioneController {

    private final ISottomissioneService sottomissioneService;

    public SottomissioneController(ISottomissioneService sottomissioneService) {
        this.sottomissioneService = sottomissioneService;
    }

    @PostMapping("/invia")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public SottomissioneResponse inviaSottomissione(@RequestBody SottomissioneRequest request){
        if(request == null) throw new IllegalArgumentException();
        return sottomissioneService.inviaSottomissione(request);
    }

    @PutMapping("/midifica/{idSottomissione}")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public SottomissioneResponse aggiornaSottomissione(@PathVariable Long idSottomissione, @RequestParam String titolo, @RequestParam String descrizione, @RequestParam String linkRepository){
        if(idSottomissione==null) throw new IllegalArgumentException("Sottomissione non inserita");
        SottomissioneRequest request=new SottomissioneRequest();
        if(descrizione!=null) request.setDescrizione(descrizione);
        if(titolo!=null) request.setTitolo(titolo);
        if(linkRepository!=null) request.setLinkRepository(linkRepository);
        return sottomissioneService.aggiornaSottomissione(idSottomissione, request);
    }

    @GetMapping("/all/{idHackathon}")
    @PreAuthorize("hasRole('GIUDICE')")
    public List<SottomissioneResponse> getAllSottomissioni(@PathVariable Long idHackathon){
        if(idHackathon==null) throw new IllegalArgumentException();
        List<SottomissioneResponse> sottomissioneResponses=  new ArrayList<>();
        SottomissioneMapper mapp= new SottomissioneMapper();
        for(Sottomissione s: sottomissioneService.getAllSottomissioni(idHackathon)){
            sottomissioneResponses.add(mapp.toResponse(s));
        }
        return sottomissioneResponses;
    }

}
