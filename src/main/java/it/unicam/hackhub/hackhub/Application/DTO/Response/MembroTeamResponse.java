package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MembroTeamResponse {
    private UtenteResponse utenteId;
    private TeamResponse teamId;
}