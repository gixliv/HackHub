package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.SottomissioneRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.SottomissioneResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Sottomissione;
import it.unicam.hackhub.hackhub.Core.models.Team;

public class SottomissioneMapper {

    public Sottomissione toEntity(SottomissioneRequest request){
        if(request==null) return null;
        Sottomissione sottomissione= new Sottomissione();
        sottomissione.setTitolo(request.getTitolo());
        sottomissione.setDescrizione(request.getDescrizione());
        sottomissione.setLinkRepository(request.getLinkRepository());
        Team team= new Team();
        team.setId(request.getIdTeam());
        sottomissione.setTeam(team);
        Hackathon hackathon= new Hackathon();
        hackathon.setId(request.getIdHackathon());
        sottomissione.setHackathon(hackathon);
        return sottomissione;
    }

    public SottomissioneResponse toResponse(Sottomissione sottomissione){
        if(sottomissione==null) return null;
        SottomissioneResponse sottomissioneResponse=new SottomissioneResponse();
        sottomissioneResponse.setTitolo(sottomissione.getTitolo());
        sottomissioneResponse.setDescrizione(sottomissione.getDescrizione());
        sottomissioneResponse.setLinkRepository(sottomissione.getLinkRepository());
        sottomissioneResponse.setNomeTeam(sottomissione.getTeam().getNome());
        sottomissioneResponse.setNomeHackathon(sottomissione.getHackathon().getNome());
        return sottomissioneResponse;
    }
}
