package it.unicam.hackhub.hackhub.Application.DTO.Request;

import java.time.LocalDate;

public class HackathonRequest {

    private String nome;
    private String regolamento;
    private LocalDate scadenzaIscrizioni;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String luogo;
    private int dimensioneMaxTeam;
    private int numMaxTeam;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }

    public LocalDate getScadenzaIscrizioni() {
        return scadenzaIscrizioni;
    }

    public void setScadenzaIscrizioni(LocalDate scadenzaIscrizioni) {
        this.scadenzaIscrizioni = scadenzaIscrizioni;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public int getDimensioneMaxTeam() {
        return dimensioneMaxTeam;
    }

    public void setDimensioneMaxTeam(int dimensioneMaxTeam) {
        this.dimensioneMaxTeam = dimensioneMaxTeam;
    }

    public int getNumMaxTeam() {return numMaxTeam;}

    public void setNumMaxTeam(int numMaxTeam) {this.numMaxTeam = numMaxTeam;}
}
