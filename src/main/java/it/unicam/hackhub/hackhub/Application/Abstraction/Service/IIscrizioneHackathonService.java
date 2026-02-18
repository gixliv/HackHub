package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Core.models.Hackathon;

public interface IIscrizioneHackathonService {

    Hackathon iscriviTeamHackathon();
    Hackathon disiscriviTeamHackathon();
}
