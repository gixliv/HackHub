package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.core.models.Team;

import java.util.List;

public interface ITeamService {
    Team creaTeam();
    Team disiscriviTeamHackathon();
    Team iscriviTeamHackathon();
    Team eliminaTeam();
    Team addMembro();
    List<Team> getMembriTeam();
    Team getMembroById();

}
