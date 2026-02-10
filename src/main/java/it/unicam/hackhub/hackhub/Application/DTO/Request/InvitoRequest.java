package it.unicam.hackhub.hackhub.Application.DTO.Request;

public class InvitoRequest {

    private String descrizione;
    private Long mittenteId;
    private Long destinatarioId;

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getMittenteId() {
        return mittenteId;
    }

    public void setMittenteId(Long mittenteId) {
        this.mittenteId = mittenteId;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }
}
