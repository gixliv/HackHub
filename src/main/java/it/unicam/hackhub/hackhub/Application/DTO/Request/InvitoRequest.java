package it.unicam.hackhub.hackhub.Application.DTO.Request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvitoRequest {

    private String descrizione;
    private Long mittenteId;
    private Long destinatarioId;

}
