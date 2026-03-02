package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Core.models.Hackathon;

public interface IHackathonService {
    //todo da rivedere con it2
    Hackathon iscriviTeamHackathon();

    Hackathon disiscriviTeamHackathon();
}
