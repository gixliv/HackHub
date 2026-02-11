package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HackathonResponse {
    private final Long id;
    private final String nome;
    private final String regolamento;
    private final LocalDate scadenzaIscrizioni;
    private final LocalDate dataInizio;
    private final LocalDate dataFine;
    private final String luogo;
    private final int dimensioneMaxTeam;
}