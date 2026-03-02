package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Request.MembroTeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;

public interface IMembriTeamService {
    //todo non sono void ma booleam e non ci vanno request
    void disiscriviTeamHackathon(TeamRequest teamRequest, HackathonRequest hackathonRequest);

    void iscriviTeamHackathon(TeamRequest teamRequest, HackathonRequest hackathonRequest);

    //todo idem qua nessuna request
    MembroTeam getMembroById(MembroTeamRequest request);

    //todo non credo torni un membro ma un booleano, da vedre bene
    MembroTeam addMembro(MembroTeamRequest request);

}
