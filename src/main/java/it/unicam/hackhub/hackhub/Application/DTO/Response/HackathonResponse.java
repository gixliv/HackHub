package it.unicam.hackhub.hackhub.Application.DTO.Response;

import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class HackathonResponse {
    private String nome;
    private String regolamento;
    private LocalDate scadenzaIscrizioni;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private int dimensioneMaxTeam;
    private int numMaxTeam;
    private StatoHackathon stato;
    private List<Long> teamsId;
}