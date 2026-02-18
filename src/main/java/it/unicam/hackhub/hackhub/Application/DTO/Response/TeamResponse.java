package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TeamResponse {
    private String nome;
    private String descrizione;
    private int numeroMassimoComponenti;
    private List<Long> membriId;
}