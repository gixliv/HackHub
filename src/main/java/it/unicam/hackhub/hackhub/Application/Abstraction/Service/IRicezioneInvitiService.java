package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.core.models.Invito;

import java.util.List;

public interface IRicezioneInvitiService {

    List<Invito> getAllInviti();
    Invito accettaInvito();
}
