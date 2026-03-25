package it.unicam.hackhub.hackhub.Core.enums;

public enum Ruolo {
    MEMBRO_TEAM,
    CREATORE_TEAM,
    UTENTE_GENERICO,
    ORGANIZZATORE,
    MENTORE,
    GIUDICE;

    public boolean isRuoloStaff(){
        return this == GIUDICE || this == ORGANIZZATORE || this == MENTORE;
    }

    public boolean isRuoloUtente(){
        return this == MEMBRO_TEAM || this == CREATORE_TEAM || this == UTENTE_GENERICO;
    }
}
