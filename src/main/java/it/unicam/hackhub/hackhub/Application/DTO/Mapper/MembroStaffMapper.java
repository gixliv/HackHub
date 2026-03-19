package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.MembroStaffRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.MembroStaffResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;

import java.util.ArrayList;
import java.util.List;

public class MembroStaffMapper {

    public MembroStaff toEntity(MembroStaffRequest membroStaffRequest){
        if(membroStaffRequest==null) return null;

        MembroStaff membroStaff= new MembroStaff();
        membroStaff.setCodiceFiscale(membroStaffRequest.getCodiceFiscale());
        Hackathon hackathon= new Hackathon();
        hackathon.setNome(membroStaffRequest.getNomeHackathon());
        membroStaff.setHackathon(hackathon);

        return membroStaff;
    }

    public MembroStaffResponse toResponse(MembroStaff membroStaff){
        if(membroStaff==null) return null;
        MembroStaffResponse membroStaffResponse= new MembroStaffResponse();
        membroStaffResponse.setCodiceFiscale(membroStaff.getCodiceFiscale());
        membroStaffResponse.setNomeHackathon(membroStaff.getHackathon().getNome());

        List<String> hackathons=new ArrayList<>();
        if(membroStaff.getHackathonsOrganizzatore()!=null){
            for(Hackathon hackathon: membroStaff.getHackathonsOrganizzatore()){
                hackathons.add(hackathon.getNome());
            }
        }
        membroStaffResponse.setNomeHackathonsOrganizzatore(hackathons);

        List<String> hackathonsG =new ArrayList<>();
        if(membroStaff.getHackathonsGiudice()!=null){
            for(Hackathon hackathon: membroStaff.getHackathonsGiudice()){
                hackathonsG.add(hackathon.getNome());
            }
        }
        membroStaffResponse.setNomeHackathonsGiudice(hackathonsG);

        List<String> hackathonsM =new ArrayList<>();
        if(membroStaff.getHackathonsMentore()!=null){
            for(Hackathon hackathon: membroStaff.getHackathonsMentore()){
                hackathonsM.add(hackathon.getNome());
            }
        }
        membroStaffResponse.setNomeHackathonsMentori(hackathonsM);

        return membroStaffResponse;
    }
}
