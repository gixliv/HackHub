package it.unicam.hackhub.hackhub.Application.DTO.Response;

import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HackathonStaffResponse {
    private String nome;
    private String regolamento;
    private LocalDate scadenzaIscrizioni;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private int dimensioneMaxTeam;
    private int numMaxTeam;
    private StatoHackathon stato;
    private List<String> nomeTeams;
    private List<String> organizzatore;
    private List<String> giudice;
    private List<List<String>> mentori;
}
