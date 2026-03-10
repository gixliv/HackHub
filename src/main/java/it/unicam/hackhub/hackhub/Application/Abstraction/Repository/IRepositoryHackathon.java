package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Team;

import java.util.List;
import java.util.Optional;

public interface IRepositoryHackathon {
    //TODO
    Optional<Hackathon> findHackathonById(Long id);
    Optional<Hackathon> findHackathonByName(String name);
    Optional<Hackathon> updateHackathon(Hackathon hackathon);
    List<Hackathon> getAllHackathon();
}
