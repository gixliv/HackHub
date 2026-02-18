package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.MembroTeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.MembroTeamResponse;
import it.unicam.hackhub.hackhub.core.models.MembroTeam;
import it.unicam.hackhub.hackhub.core.models.Team;
import it.unicam.hackhub.hackhub.core.models.Utente;

public class MembroTeamMapper {

    public MembroTeam toEntity(MembroTeamRequest membroTeamRequest) {
        if (membroTeamRequest == null) return null;

        MembroTeam membroTeam = new MembroTeam();

        Team team = new Team();
        team.setId(membroTeamRequest.getTeamId());
        membroTeam.setTeam(team);

        Utente utente = new Utente();
        utente.setId(membroTeamRequest.getUtenteId());
        membroTeam.setUtente(utente);

        return membroTeam;
    }

    public MembroTeamResponse toResponse(MembroTeam membroTeam) {
        if (membroTeam == null) return null;

        MembroTeamResponse membroTeamResponse = new MembroTeamResponse();
        membroTeamResponse.setTeamId(membroTeam.getTeam().getId());
        membroTeamResponse.setUtenteId(membroTeam.getUtente().getId());
        return membroTeamResponse;
    }

}
