package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeamResponse {
    private final Long id;
    private final String nome;
    private final String descrizione;
    private final int numeroMassimoComponenti;
    private final UtenteResponse creatore;
    private final List<MembroTeamResponse> membri;
    private final HackathonResponse hackathon;
}