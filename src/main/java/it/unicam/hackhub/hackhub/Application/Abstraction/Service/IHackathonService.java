package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Team;

import java.util.List;
import java.util.Optional;

public interface IHackathonService {

    Hackathon iscriviTeamHackathon(Long idHackathon, Long idUtente);

    Hackathon disiscriviTeamHackathon(Long idHackathon, Long idUtente);

    List<Team> getAllTeams(Long idHackathon);

    List<Hackathon> getAllHackathon();

    Optional<Hackathon> getHackathonByName(String name);
}
