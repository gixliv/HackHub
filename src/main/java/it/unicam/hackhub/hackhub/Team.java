package it.unicam.hackhub.hackhub;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Team {
    @Getter
    @Setter
    private int numeroMassimoComponenti;

    @Getter
    @Setter
    private String nome;

    private MembroTeam membriTeam[];

    private CreatoreTeam creatoreTeam;
}