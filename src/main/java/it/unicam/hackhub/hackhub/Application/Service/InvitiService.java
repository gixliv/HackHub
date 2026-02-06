package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IInvioInvitiService;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IRicezioneInvitiService;
import it.unicam.hackhub.hackhub.core.models.Invito;

import java.util.List;

public class InvitiService implements IInvioInvitiService, IRicezioneInvitiService {
    @Override
    public Invito invitaUtente() {
        return null;
    }

    @Override
    public List<Invito> getAllInviti() {
        return List.of();
    }

    @Override
    public Invito accettaInvito() {
        return null;
    }
}
