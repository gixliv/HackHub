package it.unicam.hackhub.hackhub.Application.Abstraction.Service;

import it.unicam.hackhub.hackhub.Core.models.Invito;
import it.unicam.hackhub.hackhub.Core.models.Utente;

import java.util.List;

public interface IInvioInvitiService {

    Invito invitaUtente(Utente utente);
}
