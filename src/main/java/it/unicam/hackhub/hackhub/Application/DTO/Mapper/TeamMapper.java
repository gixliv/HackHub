package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.TeamResponse;
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
        //nell' entità team la lista di membri del team contiene oggetti di tipo utente
        //per la gestione della visibilità di alcune informazioni, viene selezionato solo il nome dei membri del team per la response
        List<String> membri = new ArrayList<>();
        if (team.getMembri() != null) {
            for (Utente membro : team.getMembri()) {
                membri.add(membro.getNome());
            }
        }
        teamresponse.setNomeMembri(membri);
        teamresponse.setNomeCreatore(team.getCreatore().getNome());
        if (team.getHackathon()!=null)teamresponse.setNomeHackathon(team.getHackathon().getNome());
        return  teamresponse;
    }
}
