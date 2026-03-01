package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;

public interface ITeamService {
    Team creaTeam(TeamRequest request);
    boolean eliminaTeam(Long id);
    List<MembroTeam> getMembriTeam(Long id);
    Utente getCreatoreTeam(Long id);

    Team updateTeam(Long id, int numeroMassimoComponenti, String nome, String descrizione);

    Team removeHackathon(Long idTeam);
    Team addHackathon(Long idTeam, Hackathon hackathon);
}
