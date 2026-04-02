package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RichiestaSupportoResponse {
    private String descrizione;
    private String nomeMentore;
    private String nomeHackathon;
    private String nomeTeam;
}
