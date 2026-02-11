package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MembroTeamResponse {
    private final Long id;
    private final UtenteResponse utente;
    private final TeamResponse team;
}