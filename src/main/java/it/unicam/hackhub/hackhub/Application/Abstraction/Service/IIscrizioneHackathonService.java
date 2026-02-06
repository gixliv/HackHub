package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.core.models.Hackathon;

public interface IIscrizioneHackathonService {

    Hackathon iscriviTeamHackathon();
    Hackathon disiscriviTeamHackathon();
}
