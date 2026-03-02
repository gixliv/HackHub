package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IMembriTeamService;
import it.unicam.hackhub.hackhub.Application.DTO.Request.MembroTeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Request.TeamRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroTeam;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembriTeamService implements IMembriTeamService {

    private final IRepositoryMembriTeam repositoryMembriTeam;
    private final IRepositoryUtenti repositoryUtenti;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryHackathon repositoryHackathon;

    public MembriTeamService(IRepositoryMembriTeam repositoryMembriTeam, IRepositoryUtenti repositoryUtenti, IRepositoryTeam repositoryTeam, IRepositoryHackathon repositoryHackathon) {
        this.repositoryMembriTeam = repositoryMembriTeam;
        this.repositoryUtenti = repositoryUtenti;
        this.repositoryTeam = repositoryTeam;
        this.repositoryHackathon = repositoryHackathon;
    }

    @Override
    public void disiscriviTeamHackathon(TeamRequest teamRequest, HackathonRequest hackathonRequest) {

        Team team = repositoryTeam.findByName(teamRequest.getNome()).orElse(null);
        if (team == null) return;

        List<MembroTeam> membri = repositoryMembriTeam.findAllByTeam(team);
        for (MembroTeam membro : membri) {
            repositoryMembriTeam.delete(membro);
        }

        Hackathon hackathon = repositoryHackathon.findByName(hackathonRequest.getNome()).orElse(null);
        if (team.getHackathon() != null && team.getHackathon().equals(hackathon)) {
            team.setHackathon(null);
            repositoryTeam.save(team);
        }
    }

    @Override
    public void iscriviTeamHackathon(TeamRequest teamRequest, HackathonRequest hackathonRequest) {

        Team team = repositoryTeam.findByName(teamRequest.getNome()).orElse(null);
        if (team == null) return;

        Hackathon hackathon = repositoryHackathon.findByName(hackathonRequest.getNome()).orElse(null);
        if (hackathon == null) return;

        team.setHackathon(hackathon);
        repositoryTeam.save(team);
    }

    @Override
    public MembroTeam addMembro(MembroTeamRequest membroTeamRequest) {

        Utente utente = repositoryUtenti.findById(membroTeamRequest.getUtenteId()).orElse(null);
        Team team = repositoryTeam.findById(membroTeamRequest.getTeamId()).orElse(null);

        if (utente == null || team == null) return null;

        Optional<MembroTeam> existing = Optional.ofNullable(repositoryMembriTeam.findByUserId(utente.getId()));
        if (existing.isPresent() && existing.get().getTeam().equals(team)) {
            return null;
        }

        MembroTeam membroTeam = new MembroTeam();
        membroTeam.setUtente(utente);
        membroTeam.setTeam(team);

        return repositoryMembriTeam.save(membroTeam);
    }

    @Override
    public MembroTeam getMembroById(MembroTeamRequest membroTeamRequest) {
        return repositoryMembriTeam.findByUserId(membroTeamRequest.getUtenteId());
    }
}