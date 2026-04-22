package it.unicam.hackhub.hackhub.Application.DTO.Mapper;

import it.unicam.hackhub.hackhub.Application.DTO.Request.SegnalazioneRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.SegnalazioneResponse;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Core.models.Segnalazione;
import it.unicam.hackhub.hackhub.Core.models.Team;

public class SegnalazioneMapper {
    public Segnalazione toEntity(SegnalazioneRequest request) {
        if (request == null) return null;
        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setTitolo(request.getTitolo());
        segnalazione.setDescrizione(request.getDescrizione());

        Team team = new Team();
        team.setId(request.getIdTeam());
        segnalazione.setTeamSegnalato(team);

        Hackathon hackathon = new Hackathon();
        hackathon.setId(request.getIdHackathon());
        segnalazione.setHackathon(hackathon);

        MembroStaff mentore = new MembroStaff();
        mentore.setId(request.getIdMentore());
        segnalazione.setMentore(mentore);

        return segnalazione;
    }

    public SegnalazioneResponse toResponse(Segnalazione segnalazione) {
        if (segnalazione == null) return null;
        SegnalazioneResponse response = new SegnalazioneResponse();
        response.setTitolo(segnalazione.getTitolo());
        response.setDescrizione(segnalazione.getDescrizione());
        response.setNomeTeamSegnalato(segnalazione.getTeamSegnalato().getNome());
        response.setNomeHackathon(segnalazione.getHackathon().getNome());
        response.setUsernameMentore(segnalazione.getMentore().getUsername());
        return response;
    }
}
