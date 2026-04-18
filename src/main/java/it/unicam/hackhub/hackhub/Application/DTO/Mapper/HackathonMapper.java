package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.HackathonResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Core.models.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        hackathon.setNumMaxTeam(hackathonRequest.getNumMaxTeam());

        MembroStaff giudice= new MembroStaff();
        giudice.setId(hackathonRequest.getIdGiudice());
        hackathon.setGiudice(giudice);

        List<MembroStaff> mentori=new ArrayList<>();
        MembroStaff mentore= new MembroStaff();
        for(Long idMentore: hackathonRequest.getIdMentori()){
            mentore.setId(idMentore);
            mentori.add(mentore);
        }
        hackathon.setMentori(mentori);

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
        response.setNumMaxTeam(hackathon.getNumMaxTeam());
        response.setStato(hackathon.getStato());

        //nell' entità hackathon la lista di team contiene oggetti di tipo team
        //per la gestione della visibilità di alcune informazioni, viene selezionato solo il nome dei team per la response
        List<String> teams=new ArrayList<>();
        if(hackathon.getTeams()!=null)
            for(Team team: hackathon.getTeams()){
                teams.add(team.getNome());
            }
        response.setNometeams(teams);
        response.setNomeOrganizzatore(hackathon.getOrganizzatore().getNome());
        response.setNomeGiudice(hackathon.getGiudice().getNome());

        //nell' entità hackathon la lista di mentori contiene oggetti di tipo MembroStaff
        //per la gestione della visibilità di alcune informazioni, viene selezionato solo il nome dei mentori per la response
        List<String> nomiMentori=new ArrayList<>();
        if(hackathon.getMentori()!=null)
            for(MembroStaff membri: hackathon.getMentori()){
                nomiMentori.add(membri.getNome());
            }
        response.setNomiMentori(nomiMentori);
        return response;
    }
}
