package it.unicam.hackhub.hackhub.Application.Builder;

import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.*;

import java.time.LocalDate;
import java.util.List;

//Definizione del design pattern builder utilizzato per la creazione di un hackathon
public interface IHackathonBuilder {
    IHackathonBuilder nome(String nome);
    IHackathonBuilder regolamento(String regolamento);
    IHackathonBuilder scadenzaIscrizioni(LocalDate scadenzaIscrizioni);
    IHackathonBuilder dataInizio(LocalDate dataInizio);
    IHackathonBuilder dataFine(LocalDate dataFine);
    IHackathonBuilder luogo(String luogo);
    IHackathonBuilder dimensioneMaxTeam(int dimensioneMaxTeam);
    IHackathonBuilder numMaxTeam(int numMaxTeam);
    IHackathonBuilder teams(List<Team> teams);
    IHackathonBuilder organizzatore(MembroStaff organizzatore);
    IHackathonBuilder giudice(MembroStaff giudice);
    IHackathonBuilder mentori(List<MembroStaff> mentori);
    IHackathonBuilder sottomissioni(List<Sottomissione> sottomissioni);
    IHackathonBuilder richiesteSupporto(List<RichiestaSupporto> richiesteSupporto);
    Hackathon build();
}
