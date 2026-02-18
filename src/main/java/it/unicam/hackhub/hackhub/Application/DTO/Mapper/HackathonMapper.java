package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.HackathonResponse;
import it.unicam.hackhub.hackhub.core.models.Hackathon;
import org.springframework.stereotype.Component;

@Component
public class HackathonMapper {

    public Hackathon toEntity(HackathonRequest hackathonRequest) {
        if (hackathonRequest == null) return  null;

        Hackathon hackathon = new Hackathon();
        hackathon.setNome(hackathonRequest.getNome());
        hackathon.setRegolamento(hackathonRequest.getRegolamento());
        hackathon.setScadenzaIscrizioni(hackathonRequest.getScadenzaIscrizioni());
        hackathon.setDataInizio(hackathonRequest.getDataInizio());
        hackathon.setDataFine(hackathonRequest.getDataFine());
        hackathon.setLuogo(hackathonRequest.getLuogo());
        hackathon.setDimensioneMaxTeam(hackathonRequest.getDimensioneMaxTeam());
        return hackathon;
    }

    public HackathonResponse toResponse(Hackathon hackathon) {
        if (hackathon == null) return null;

        HackathonResponse response = new HackathonResponse();
        response.setNome(hackathon.getNome());
        response.setRegolamento(hackathon.getRegolamento());
        response.setScadenzaIscrizioni(hackathon.getScadenzaIscrizioni());
        response.setDataInizio(hackathon.getDataInizio());
        response.setDataFine(hackathon.getDataFine());
        response.setLuogo(hackathon.getLuogo());
        response.setDimensioneMaxTeam(hackathon.getDimensioneMaxTeam());
        return response;
    }
}
