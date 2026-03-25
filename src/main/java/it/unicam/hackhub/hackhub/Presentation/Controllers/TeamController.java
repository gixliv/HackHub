package it.unicam.hackhub.hackhub.Presentation.Controllers;

import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ITeamService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.HackathonMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.TeamMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.UtenteMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.HackathonResponse;
import it.unicam.hackhub.hackhub.Application.DTO.Response.TeamResponse;
import it.unicam.hackhub.hackhub.Application.DTO.Response.UtenteResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/team")
public class TeamController {
    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    @PreAuthorize("hasRole('UTENTE_GENERICO')")
    public String creaTeam(@RequestBody TeamRequest teamRequest) {
        if (teamRequest == null) {
            throw new IllegalArgumentException();
        }
        teamService.creaTeam(teamRequest);
        return "Team creato";
    }

    //lista di utenti membri di uno specifico team
    @GetMapping("/membri/{teamId}")
    public List<UtenteResponse> getMembriTeam(@PathVariable Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException();
        }
        List<UtenteResponse> response=new ArrayList<>();
        UtenteMapper utenteMapper= new UtenteMapper();
        List<Utente> utenti=teamService.getMembriTeam(teamId);
        for (Utente utente: utenti){
            response.add(utenteMapper.toResponse(utente));
        }
        return response;
    }

    @GetMapping("/creatore/{teamId}")
    public UtenteResponse getCreatoreTeam(@PathVariable Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException();
        }
        Utente utente = teamService.getCreatoreTeam(teamId);
        UtenteMapper  utenteMapper = new UtenteMapper();
        return utenteMapper.toResponse(utente);
    }

    @DeleteMapping("/{idTeam}")
    @PreAuthorize("hasRole('CREATORE_TEAM')")
    public String deleteTeam(@PathVariable Long idTeam) {
        if (idTeam == null) {
            throw new IllegalArgumentException();
        }
        teamService.eliminaTeam(idTeam);
        return "Team eliminato";
    }

    //modifica, da parte del creatore del team, di alcuni o tutti i parametri di un team che sono stati inseriti alla sua creazione, attraverso file jason
    @PutMapping("/{idTeam}")
    @PreAuthorize("hasRole('CREATORE_TEAM')")
    public TeamResponse updateTeam(@PathVariable Long idTeam, @RequestBody TeamRequest teamRequest) {
        if (teamRequest == null) {
            throw new IllegalArgumentException();
        }
        Team team = teamService.updateTeam(idTeam, teamRequest.getNumeroMassimoComponenti(), teamRequest.getNome(), teamRequest.getDescrizione());
        TeamMapper  teamMapper = new TeamMapper();
        return teamMapper.toResponse(team);
    }

    //espulsione, da parte del creatore di uno specifico team, di un membro
    @PutMapping("/espelli/{idUtente}")
    @PreAuthorize("hasRole('CREATORE_TEAM')")
    public String eliminaMembro(@PathVariable Long idUtente, @RequestParam Long idTeam) {
        teamService.eliminaMembro(idUtente, idTeam);
        return "Membro eliminato";
    }

    //visualizzazione di un hackathon a cui è iscritto uno specifico team
    @GetMapping("/hackathon/{idTeam}")
    public HackathonResponse getHackathon(@PathVariable Long idTeam) {
        Hackathon hackathon = teamService.getHackathon(idTeam);
        HackathonMapper hackathonMapper = new HackathonMapper();
        return  hackathonMapper.toResponse(hackathon);
    }

    //lista di tutti i team presenti nel sistema
    @GetMapping
    public List<TeamResponse> getAllTeams() {
        TeamMapper teamMapper = new TeamMapper();
        List<TeamResponse> response=new ArrayList<>();
        for (Team team: teamService.getAllTeams()) {
            response.add(teamMapper.toResponse(team));
        }
        return response;
    }

    //uscita spontanea dal team da parte di un membro
    @PutMapping("/abbandona/{idTeam}")
    @PreAuthorize("hasRole('MEMBRO_TEAM')")
    public String abbandonaTeam( @RequestParam Long idUtente, @PathVariable Long idTeam) {
        if(teamService.abbandonaTeam(idUtente, idTeam)) return "Team abbandonato";
        return "Impossibile abbandonare il team";
    }


}
