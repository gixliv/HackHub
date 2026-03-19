package it.unicam.hackhub.hackhub.Application.DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MembroStaffResponse {
    private String codiceFiscale;
    private String nomeHackathon;
    private List<String> nomeHackathonsOrganizzatore;
    private List<String> nomeHackathonsGiudice;
    private List<String> nomeHackathonsMentori;

}
