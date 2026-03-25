package it.unicam.hackhub.hackhub.Presentation.Controllers;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IHackathonService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.HackathonMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.TeamMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Response.HackathonResponse;
import it.unicam.hackhub.hackhub.Application.DTO.Response.TeamResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/hackathon")
public class HackathonController {

    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService, IRepositoryHackathon repositoryHackathon) {
        this.hackathonService = hackathonService;
    }

    //l'iscrizione del team all'hackathon avviene attraverso l'id, la scelta avverrà da una lista di hackathon disponibili
    @PutMapping("/iscrivi/{idHackathon}")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public HackathonResponse iscriviTeamHackathon(@PathVariable Long idHackathon, @RequestParam Long idUtente) {
        if (idHackathon == null || idUtente == null) throw new IllegalArgumentException();
        //dopo l'iscrizione del team all'hackathon, viene effettuato il mapping dell'hackathon per ottenere un hackathonResponse,
        //saranno visibili oltre a delle informazioni, anche la lista dei nomi dei team iscritti all'hackathon
        HackathonMapper mapper = new HackathonMapper();
        Hackathon hackathon = hackathonService.iscriviTeamHackathon(idHackathon, idUtente);
        return mapper.toResponse(hackathon);
    }

    //l'iscrizione del team all'hackathon avviene attraverso il nome, che verrà inserito in una search bar, per la ricerca tra gli hackathon presenti nel sistema.
    @PutMapping("/iscrivi/nome={nomeHackathon}")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public HackathonResponse iscriviTeamHackathon(@PathVariable String nomeHackathon, @RequestParam Long idUtente) {
        if (nomeHackathon == null || idUtente == null) throw new IllegalArgumentException();
        HackathonMapper mapper = new HackathonMapper();
        Hackathon hackathon = hackathonService.getHackathonByName(nomeHackathon);
        hackathon = hackathonService.iscriviTeamHackathon(hackathon.getId(), idUtente);
        return mapper.toResponse(hackathon);
    }

    @PostMapping("/disiscrivi/{idHackathon}")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public String disiscriviTeamHackathon(@PathVariable Long idHackathon, @RequestParam Long idUtente) {
        if (idHackathon == null || idUtente == null) throw new IllegalArgumentException();
        hackathonService.disiscriviTeamHackathon(idHackathon, idUtente);
        return "Team disiscritto";
    }

    //lista di tutti i nomi dei team che si sono iscritti ad uno specifico hackathon
    @GetMapping("/teams/{idHackathon}")
    public List<TeamResponse> getAllTeams(@PathVariable Long idHackathon) {
        if (idHackathon == null) throw new IllegalArgumentException();
        List<TeamResponse> teamResp = new ArrayList<>();
        List<Team> teams = hackathonService.getAllTeams(idHackathon);
        TeamMapper map = new TeamMapper();
        for (Team t : teams) {
            teamResp.add(map.toResponse(t));
        }
        return teamResp;
    }

    //lista di tutti i nomi degli hackathon presenti nel sistema
    @GetMapping
    public List<String> getAllHackathons() {
        List<String> nomiResponse = new ArrayList<>();
        List<Hackathon> hackathons = hackathonService.getAllHackathon();
        if (!hackathons.isEmpty()) {
            for (Hackathon h : hackathons) {
                nomiResponse.add(h.getNome());
            }
            return nomiResponse;
        }
        throw new EntityNotFoundException();
    }

    //in seguito a getAllHackathons, dalla lista dei nomi, l'utente può scegliere un hackathon.
    //l'utente inserisce il nome dell'hackathon per avere delle informazioni a riguardo
    @GetMapping("/{nomeHackaton}")
    public HackathonResponse getHackaton(@PathVariable String nomeHackathon) {
        if (nomeHackathon == null) throw new IllegalArgumentException();
        Hackathon hackaton = hackathonService.getHackathonByName(nomeHackathon);
        HackathonMapper map = new HackathonMapper();
        return map.toResponse(hackaton);
    }

    //il membro dello staff, attraverso il suo id, può visualizzare tutti i nomi degli hackathon a cui viene assegnato come staff
    @GetMapping("/myHackatons/{idUtente}")
    @PreAuthorize("hasRole('MEMBRO_STAFF')")
    public List<String> getAllMyHackatons(@PathVariable Long idUtente){
        List<String> nomiResponse = new ArrayList<>();
        List<Hackathon> hackathons = hackathonService.getAllMyHackathon(idUtente);
        if(!hackathons.isEmpty()) {
            for(Hackathon h : hackathons) {
                nomiResponse.add(h.getNome());
            }
            return nomiResponse;
        }
        throw new EntityNotFoundException();
    }

}