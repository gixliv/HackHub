package it.unicam.hackhub.hackhub.Application.DTO.Request;

import it.unicam.hackhub.hackhub.Core.models.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SegnalazioneRequest {
    private String titolo;
    private String descrizione;
    private Long idTeam;
    private Long idMentore;
    private Long idHackathon;
}
