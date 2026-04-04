package it.unicam.hackhub.hackhub.Application.DTO.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RichiestaSupportoRequest {
    private String descrizione;
    private Long mentoreId;
    private Long teamId;
    private Long hackathonId;
}
