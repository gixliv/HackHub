package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;

public interface ITeamService {
    Team creaTeam(TeamRequest request);
    boolean eliminaTeam(Long idTeam);
    boolean eliminaMembro(Long idUtente, Long idTeam);
    List<Utente> getMembriTeam(Long idTeam);
    Utente getCreatoreTeam(Long idUtente);
    //todo verificare opzionalità parametri
    Team updateTeam(Long idUtente, int numeroMassimoComponenti, String nome, String descrizione);
    boolean setMembro(Long idUtente, Long idTeam);
    Utente getMembroById(Long idUtente, Long idTeam);
    Hackathon getHackathon(Long idTeam);
    List<Team> getAllTeams();
}
