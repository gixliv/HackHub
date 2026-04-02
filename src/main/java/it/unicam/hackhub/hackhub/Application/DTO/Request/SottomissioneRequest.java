package it.unicam.hackhub.hackhub.Application.DTO.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SottomissioneRequest {

    private String titolo;
    private String descrizione;
    private String linkRepository;
    private Long idTeam;
}
