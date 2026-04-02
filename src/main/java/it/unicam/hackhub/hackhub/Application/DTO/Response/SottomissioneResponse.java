package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SottomissioneResponse {

    private String titolo;
    private String descrizione;
    private String linkRepository;
    private String nomeHackathon;
    private String nomeTeam;
}
