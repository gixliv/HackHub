package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Core.models.Team;

import java.util.List;

public interface ITeamService {
    Team creaTeam();
    Team disiscriviTeamHackathon();
    Team iscriviTeamHackathon();
    Team getMembroById();
    Team addMembro();
    Team eliminaTeam();
    List<Team> getMembriTeam();
    Team getCreatoreTeam();

}
