package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ITeamService;
import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
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
    public boolean eliminaTeam(Long id) {
        Team team = repositoryTeam.findById(id).orElseThrow(null);
        repositoryTeam.eliminaTeam(team.getId());
        return repositoryTeam.findById(team.getId()).isPresent();
    }

    @Override
    public List<MembroTeam> getMembriTeam(Long id) {
        Team team = repositoryTeam.findById(id).orElseThrow(null);
        return team.getMembri();
    }

    @Override
    public Utente getCreatoreTeam(Long id) {
        Team team = repositoryTeam.findById(id).orElseThrow(null);
        return team.getCreatore();
    }

    @Override
    public Team updateTeam(Long id, int numeroMassimoComponenti, String nome, String descrizione){
        Team team = repositoryTeam.findById(id).orElseThrow(null);
        if (nome != null && !nome.isBlank()) team.setNome(nome);
        if (descrizione != null && !descrizione.isBlank()) team.setDescrizione(descrizione);
        if (numeroMassimoComponenti > 0) team.setNumeroMassimoComponenti(numeroMassimoComponenti);
        return repositoryTeam.updateTeam(team).orElseThrow(null);
    }
}
