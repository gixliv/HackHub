package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;

import java.util.List;

public interface IHackathonService {

    Hackathon iscriviTeamHackathon(Long idHackathon, Long idUtente);

    Hackathon disiscriviTeamHackathon(Long idHackathon, Long idUtente);

    List<Team> getAllTeams(Long idHackathon);

    List<Hackathon> getAllHackathon();

    Hackathon getHackathonByName(String name);

    List<Hackathon> getAllMyHackathon(Long idUtente);

    boolean annullaHackathon(Long idHackathon);

    boolean addMentore(Long idUtente, Long idHackathon);

    boolean eliminaHackathon(Long idHackathon);

    Hackathon creaHackathon(HackathonRequest request, Long idOrganizzatore);

    boolean espelliTeam(Long idTeam, Long idHackathon);

    Hackathon changeStato(Long idHackathon);
}
