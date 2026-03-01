package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.TeamResponse;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper {
    public Team toEntity(TeamRequest teamRequest) {
        if (teamRequest == null) return null;
        Team team = new Team();
        team.setNumeroMassimoComponenti(teamRequest.getNumeroMassimoComponenti());
        team.setNome(teamRequest.getNome());
        team.setDescrizione(teamRequest.getDescrizione());
        Utente creatore = new Utente();
        creatore.setId(teamRequest.getCreatore());
        team.setCreatore(creatore);
        return  team;
    }

    public TeamResponse toResponse(Team team) {
        if (team == null) return null;
        TeamResponse teamresponse = new TeamResponse();
        teamresponse.setNome(team.getNome());
        teamresponse.setDescrizione(team.getDescrizione());
        teamresponse.setNumeroMassimoComponenti(team.getNumeroMassimoComponenti());
        List<Long> membri = new ArrayList<>();
        if (team.getMembri() != null) {
            for (MembroTeam membro : team.getMembri()) {
                membri.add(membro.getId());
            }
        }
        teamresponse.setMembriId(membri);
        teamresponse.setCreatoreId(team.getCreatore().getId());
        return  teamresponse;
    }
}
