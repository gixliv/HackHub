package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ITeamService;
import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;

public class TeamService implements ITeamService {
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryUtenti repositoryUtenti;

    public TeamService(IRepositoryTeam repositoryTeam, IRepositoryUtenti repositoryUtenti) {
        this.repositoryTeam = repositoryTeam;
        this.repositoryUtenti = repositoryUtenti;
    }


    @Override
    public Team creaTeam(TeamRequest request) {
        Team team = new Team();
        Utente creatore = repositoryUtenti.findById(request.getCreatore()).orElseThrow(null);
        if (creatore.getRuolo() == Ruolo.CREATORE_TEAM) throw new RuntimeException("Sei già creatore di un team");
        List<Team> allTeam = repositoryTeam.getAll();
        for (Team t : allTeam) {
            if (t.getNome().equals(request.getNome())) {
                throw new RuntimeException("Team già esistente");
            }
        }
        team.setCreatore(creatore);
        team.setDescrizione(request.getDescrizione());
        team.setNome(request.getNome());
        team.setNumeroMassimoComponenti(request.getNumeroMassimoComponenti());
        repositoryTeam.insertInto(team);
        return team;
    }

    @Override
    public Team disiscriviTeamHackathon() {
        return null;
    }

    @Override
    public Team iscriviTeamHackathon() {
        return null;
    }

    @Override
    public Team eliminaTeam() {
        return null;
    }

    @Override
    public Team addMembro() {
        return null;
    }

    @Override
    public List<Team> getMembriTeam() {
        return List.of();
    }

    @Override
    public Team getMembroById() {
        return null;
    }

    @Override
    public Team getCreatoreTeam() {
        return null;
    }
}
