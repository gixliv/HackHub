package it.unicam.hackhub.hackhub.Application.DTO.Response;

import it.unicam.hackhub.hackhub.core.enums.StatoInvito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class InvitoResponse {
    private String descrizione;
    private Long mittenteId;
    private Long destinatarioId;
    private StatoInvito stato;
}