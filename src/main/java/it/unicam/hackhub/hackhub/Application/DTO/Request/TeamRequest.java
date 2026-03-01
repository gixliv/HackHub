package it.unicam.hackhub.hackhub.Application.DTO.Request;

public class TeamRequest {

    private int numeroMassimoComponenti;
    private String nome;
    private String descrizione;
    private Long creatore;

    public Long getCreatore() { return creatore;}

    public void setCreatore(Long creatore) { this.creatore = creatore;}

    public int getNumeroMassimoComponenti() {
        return numeroMassimoComponenti;
    }

    public void setNumeroMassimoComponenti(int numeroMassimoComponenti) {
        this.numeroMassimoComponenti = numeroMassimoComponenti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
