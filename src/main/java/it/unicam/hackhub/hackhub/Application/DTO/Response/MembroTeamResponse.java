package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MembroTeamResponse {
    private Long utenteId;
    private Long teamId;
}