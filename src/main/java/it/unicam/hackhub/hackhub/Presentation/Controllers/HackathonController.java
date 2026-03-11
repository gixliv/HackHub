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

@RestController
@RequestMapping("api/hackathon")
public class HackathonController {

    private final IHackathonService hackathonService;

    public HackathonController(IHackathonService hackathonService, IRepositoryHackathon repositoryHackathon) {
        this.hackathonService = hackathonService;
    }

    @PutMapping("/iscrivi/{idHackathon}")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public HackathonResponse iscriviTeamHackathon(@PathVariable Long idHackathon, @RequestParam Long idUtente){
        if(idHackathon==null || idUtente==null) throw new IllegalArgumentException();
        HackathonMapper mapper= new HackathonMapper();
        Hackathon hackathon=hackathonService.iscriviTeamHackathon(idHackathon, idUtente);
        return mapper.toResponse(hackathon);
    }

    @PutMapping("/iscrivi/nome={nomeHackathon}")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public HackathonResponse iscriviTeamHackathon(@PathVariable String nomeHackathon, @RequestParam Long idUtente){
        if(nomeHackathon==null || idUtente==null) throw new IllegalArgumentException();
        HackathonMapper mapper= new HackathonMapper();
        Hackathon hackathon=hackathonService.getHackathonByName(nomeHackathon).orElseThrow(EntityNotFoundException::new);
        hackathon=hackathonService.iscriviTeamHackathon(hackathon.getId(), idUtente);
        return mapper.toResponse(hackathon);
    }

    @PostMapping("/disiscrivi/{idHackathon}")
    @PreAuthorize("hasRole('MEMBRO_TEAM') || hasRole('CREATORE_TEAM')")
    public String disiscriviTeamHackathon(@PathVariable Long idHackathon, @RequestParam Long idUtente){
        if(idHackathon==null || idUtente==null) throw new IllegalArgumentException();
        hackathonService.disiscriviTeamHackathon(idHackathon, idUtente);
        return "Team disiscritto";
    }

    @GetMapping("/teams/{idHackathon}")
    public List<TeamResponse> getAllTeams(@PathVariable Long idHackathon){
        if(idHackathon==null) throw new IllegalArgumentException();
        List<TeamResponse> teamResp=new ArrayList<>();
        List<Team> teams= hackathonService.getAllTeams(idHackathon);
        TeamMapper map= new TeamMapper();
        for(Team t: teams){
            teamResp.add(map.toResponse(t));
        }
        return teamResp;
    }

    @GetMapping
    public List<HackathonResponse> getAllHackathon(){
        List<HackathonResponse> response= new ArrayList<>();
        List<Hackathon> hackathons=hackathonService.getAllHackathon();
        HackathonMapper mapp=new HackathonMapper();
        for(Hackathon h: hackathons){
            response.add(mapp.toResponse(h));
        }
        return response;
    }

}
