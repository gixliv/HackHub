package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.RichiestaSupportoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.RichiestaSupportoResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;
import it.unicam.hackhub.hackhub.Core.models.Team;

public class RichiestaSupportoMapper {

    public RichiestaSupporto toEntity(RichiestaSupportoRequest request) {
        if (request ==null)return null;
        RichiestaSupporto richiestaSupporto = new RichiestaSupporto();
        richiestaSupporto.setDescrizione(request.getDescrizione());

        Team team = new Team();
        team.setId(request.getTeamId());
        richiestaSupporto.setTeam(team);

        Hackathon hackathon = new Hackathon();
        hackathon.setId(team.getHackathon().getId());
        richiestaSupporto.setHackathon(hackathon);

        MembroStaff mentore = new MembroStaff();
        mentore.setId(request.getMentoreId());
        richiestaSupporto.setMentore(mentore);

        return richiestaSupporto;
    }

    public RichiestaSupportoResponse toResponse(RichiestaSupporto richiestaSupporto) {
        RichiestaSupportoResponse response = new RichiestaSupportoResponse();
        response.setDescrizione(richiestaSupporto.getDescrizione());
        response.setNomeTeam(richiestaSupporto.getTeam().getNome());
        response.setNomeHackathon(richiestaSupporto.getHackathon().getNome());
        response.setNomeMentore(richiestaSupporto.getMentore().getNome());
        return response;
    }
}
