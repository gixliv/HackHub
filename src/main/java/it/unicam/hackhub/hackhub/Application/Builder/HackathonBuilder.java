package it.unicam.hackhub.hackhub.Application.Builder;

import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.*;

import java.time.LocalDate;
import java.util.List;

//Implementazione del design pattern builder utilizzato per la creazione di un hackathon
public class HackathonBuilder implements IHackathonBuilder{
    private final Hackathon hackathon;

    public HackathonBuilder() {
        this.hackathon = new Hackathon();
    }


    @Override
    public IHackathonBuilder nome(String nome) {
        hackathon.setNome(nome);
        return this;
    }

    @Override
    public IHackathonBuilder regolamento(String regolamento) {
        hackathon.setRegolamento(regolamento);
        return this;
    }

    @Override
    public IHackathonBuilder scadenzaIscrizioni(LocalDate scadenzaIscrizioni) {
        hackathon.setScadenzaIscrizioni(scadenzaIscrizioni);
        return this;
    }

    @Override
    public IHackathonBuilder dataInizio(LocalDate dataInizio) {
        hackathon.setDataInizio(dataInizio);
        return this;
    }

    @Override
    public IHackathonBuilder dataFine(LocalDate dataFine) {
        hackathon.setDataFine(dataFine);
        return this;
    }

    @Override
    public IHackathonBuilder luogo(String luogo) {
        hackathon.setLuogo(luogo);
        return this;
    }

    @Override
    public IHackathonBuilder dimensioneMaxTeam(int dimensioneMaxTeam) {
        hackathon.setDimensioneMaxTeam(dimensioneMaxTeam);
        return this;
    }

    @Override
    public IHackathonBuilder numMaxTeam(int numMaxTeam) {
        hackathon.setNumMaxTeam(numMaxTeam);
        return this;
    }

    @Override
    public IHackathonBuilder teams(List<Team> teams) {
        hackathon.setTeams(teams);
        return this;
    }

    @Override
    public IHackathonBuilder organizzatore(MembroStaff organizzatore) {
        hackathon.setOrganizzatore(organizzatore);
        return this;
    }

    @Override
    public IHackathonBuilder giudice(MembroStaff giudice) {
        hackathon.setGiudice(giudice);
        return this;
    }

    @Override
    public IHackathonBuilder mentori(List<MembroStaff> mentori) {
        hackathon.setMentori(mentori);
        return this;
    }

    @Override
    public IHackathonBuilder sottomissioni(List<Sottomissione> sottomissioni) {
        hackathon.setSottomissioni(sottomissioni);
        return this;
    }

    @Override
    public IHackathonBuilder richiesteSupporto(List<RichiestaSupporto> richiesteSupporto) {
        hackathon.setRichiesteSupporto(richiesteSupporto);
        return this;
    }

    @Override
    public Hackathon build() {
        return hackathon;
    }
}
