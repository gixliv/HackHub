package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Response.HackathonStaffResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Core.models.Team;

import java.util.ArrayList;
import java.util.List;

public class HackathonStaffMapper {
    public HackathonStaffResponse toResponse(Hackathon hackathon){
        if(hackathon==null) return null;
        HackathonStaffResponse response=new HackathonStaffResponse();

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
        response.setNomeTeams(teams);
        List<String> organizzatore = new ArrayList<>();
        organizzatore.add(hackathon.getOrganizzatore().getNome());
        organizzatore.add(hackathon.getOrganizzatore().getCognome());
        organizzatore.add(hackathon.getOrganizzatore().getEmail());
        organizzatore.add(hackathon.getOrganizzatore().getTelefono());
        response.setOrganizzatore(organizzatore);

        List<String> giudice = new ArrayList<>();
        giudice.add(hackathon.getGiudice().getNome());
        giudice.add(hackathon.getGiudice().getCognome());
        giudice.add(hackathon.getGiudice().getEmail());
        giudice.add(hackathon.getGiudice().getTelefono());
        response.setGiudice(giudice);

        List<List<String>> mentori = new ArrayList<>();
        if(hackathon.getMentori()!=null){
            for(MembroStaff membro: hackathon.getMentori()){
                List<String> mentore = new ArrayList<>();
                mentore.add(membro.getNome());
                mentore.add(membro.getCognome());
                mentore.add(membro.getEmail());
                mentore.add(membro.getTelefono());
                mentori.add(mentore);
            }
        }
        response.setMentori(mentori);
        return response;
    }
}
