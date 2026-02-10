package it.unicam.hackhub.hackhub.Application.DTO.Request;

public class MembroTeamRequest {

    private Long utenteId;
    private Long teamId;

    public Long getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(Long utenteId) {
        this.utenteId = utenteId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
