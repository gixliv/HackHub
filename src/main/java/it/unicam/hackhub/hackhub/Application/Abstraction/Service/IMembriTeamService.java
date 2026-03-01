package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Request.MembroTeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;

public interface IMembriTeamService {
    void disiscriviTeamHackathon(TeamRequest teamRequest, HackathonRequest hackathonRequest);

    void iscriviTeamHackathon(TeamRequest teamRequest, HackathonRequest hackathonRequest);

    MembroTeam getMembroById(MembroTeamRequest request);

    MembroTeam addMembro(MembroTeamRequest request);

}
