package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.*;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IHackathonService;
import it.unicam.hackhub.hackhub.Application.Builder.HackathonBuilder;
import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.*;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositorySegnalazioneJpa;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HackathonService implements IHackathonService {

    private final IRepositoryHackathon repositoryHackathon;
    private final IRepositoryUtenti repositoryUtenti;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryMembriStaff repositoryMembriStaff;
    private final IRepositorySegnalazione repositorySegnalazione;

    public HackathonService(IRepositoryHackathon repositoryHackathon, IRepositoryUtenti repositoryUtenti, IRepositoryTeam repositoryTeam, IRepositoryMembriStaff repositoryMembriStaff, IRepositorySegnalazione repositorySegnalazione) {
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryUtenti = repositoryUtenti;
        this.repositoryTeam = repositoryTeam;
        this.repositoryMembriStaff = repositoryMembriStaff;
        this.repositorySegnalazione = repositorySegnalazione;
    }

    @Override
    public Hackathon iscriviTeamHackathon(Long idHackathon, Long idUtente) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        Utente utente = repositoryUtenti.findById(idUtente).orElseThrow(() -> new EntityNotFoundException("Utente non presente"));
        Team team = repositoryTeam.findTeamById(utente.getTeam().getId()).orElseThrow(() -> new EntityNotFoundException("Team non presente"));
        //verifica di tutti i dati necessari per l'iscrizione di un team ad un hackathon
        if (hackathon.getStato() != StatoHackathon.IN_ISCRIZIONE)
            throw new RuntimeException("Hackathon non in fase di iscrizione!!");
        if (team.getNumeroMassimoComponenti() > hackathon.getDimensioneMaxTeam())
            throw new RuntimeException("Il team ha un numero di componenti superiore a quello previsto");
        if (hackathon.getTeams().size() >= hackathon.getNumMaxTeam())
            throw new RuntimeException("Impossibile iscriversi all'hackathon numero massimo di team raggiunti");
        if (LocalDate.now().isAfter(hackathon.getScadenzaIscrizioni()))
            throw new RuntimeException("Le iscrizioni sono chiuse");

        team.setHackathon(hackathon);
        repositoryTeam.updateTeam(team);
        hackathon.getTeams().add(team);
        repositoryHackathon.updateHackathon(hackathon);

        return hackathon;
    }

    @Override
    @Transactional
    public Hackathon disiscriviTeamHackathon(Long idHackathon, Long idUtente) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        Utente utente = repositoryUtenti.findById(idUtente).orElseThrow(() -> new EntityNotFoundException("Utente non presente"));
        Team team = repositoryTeam.findTeamById(utente.getTeam().getId()).orElseThrow(() -> new EntityNotFoundException("Team non presente"));

        if (hackathon.getStato() != StatoHackathon.IN_ISCRIZIONE)
            throw new RuntimeException("Impossibile effettuare disiscrizione, l'hackathon è in " + hackathon.getStato());

        hackathon.getTeams().remove(team);
        repositoryHackathon.updateHackathon(hackathon);
        team.setHackathon(null);
        repositoryTeam.updateTeam(team);

        return hackathon;
    }

    //lista di tutti i team iscritti ad uno specifico hackathon
    @Override
    public List<Team> getAllTeams(Long idHackathon) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        return hackathon.getTeams();

    }

    //lista di tutti gli hackathon presenti nel sistema
    @Override
    public List<Hackathon> getAllHackathon() {
        return repositoryHackathon.findAllHackathon();
    }

    @Override
    public Hackathon getHackathonByName(String name) {
        return repositoryHackathon.findHackathonByName(name).orElseThrow(() -> new EntityNotFoundException("Hackaton non presente"));
    }

    //lista di tutti gli hackathon a cui un membroStaff (giudice, mentore) viene assegnato come tale
    //nel caso dell'organizzatore, lista di tutti gli hackathon che organizza
    @Override
    public List<Hackathon> getAllMyHackathon(Long idUtente) {
        MembroStaff membro = (MembroStaff) repositoryUtenti.findById(idUtente).orElseThrow(() -> new EntityNotFoundException("Hackaton non presente"));

        if (membro.getRuolo().equals(Ruolo.GIUDICE)) return membro.getHackathonsGiudice();
        else if (membro.getRuolo().equals(Ruolo.MENTORE)) return membro.getHackathonsMentore();
        else if (membro.getRuolo().equals(Ruolo.ORGANIZZATORE)) return membro.getHackathonsOrganizzatore();

        throw new EntityNotFoundException();
    }

    //eliminazione dell'hackathon da parte dell'organizzatore solo quando si trova ancora in iscrizione
    public boolean annullaHackathon(Long idHackathon) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        if (hackathon.getStato() != StatoHackathon.IN_ISCRIZIONE)
            throw new RuntimeException("Hackathon non in iscrizione");
        List<Team> teams = hackathon.getTeams();
        for (Team team : teams) {
            team.setHackathon(null);
            repositoryTeam.updateTeam(team);
        }
        List<MembroStaff> membri = hackathon.getMentori();
        membri.add(hackathon.getGiudice());
        membri.add(hackathon.getOrganizzatore());
        for (MembroStaff membro : membri) {
            membro.setHackathon(null);
            repositoryMembriStaff.updateMembroStaff(membro);
        }
        repositoryHackathon.deleteHackathonById(hackathon.getId());
        return repositoryHackathon.findHackathonById(hackathon.getId()).isEmpty();
    }

    //Crea un nuovo hackathon nel sistema
    @Override
    public Hackathon creaHackathon(HackathonRequest request, Long idOrganizzatore) {
        MembroStaff organizzatore = repositoryMembriStaff
                .findMembroStaffById(idOrganizzatore)
                .orElseThrow(() -> new EntityNotFoundException("Organizzatore non trovato"));

        if (organizzatore.getHackathon() != null) {
            throw new IllegalStateException("Organizzatore già assegnato a un hackathon");
        }

        if (repositoryHackathon.findHackathonByName(request.getNome()).isPresent()) {
            throw new EntityExistsException("Hackathon già esistente con questo nome");
        }

        MembroStaff giudice = repositoryMembriStaff.findMembroStaffById(request.getIdGiudice()).orElseThrow(() -> new EntityNotFoundException("Giudice non trovato"));

        List<MembroStaff> mentori = new ArrayList<>();
        for (Long idMentore : request.getIdMentori()) {
            MembroStaff mentore = repositoryMembriStaff.findMembroStaffById(idMentore).orElseThrow(() -> new EntityNotFoundException("Mentore non trovato"));
            mentori.add(mentore);
        }

        //creazione dell'hackathon tramite il design pattern Builder
        Hackathon hackathon = new HackathonBuilder()
                .nome(request.getNome())
                .regolamento(request.getRegolamento())
                .scadenzaIscrizioni(request.getScadenzaIscrizioni())
                .dataInizio(request.getDataInizio())
                .dataFine(request.getDataFine())
                .luogo(request.getLuogo())
                .dimensioneMaxTeam(request.getDimensioneMaxTeam())
                .numMaxTeam(request.getNumMaxTeam())
                .organizzatore(organizzatore)
                .giudice(giudice)
                .mentori(mentori)
                .build();
        hackathon.setStato(StatoHackathon.IN_ISCRIZIONE);

        //validazione dei dati per l'inserimento del nuovo hackathon
        if (hackathon.getDataInizio().isBefore(hackathon.getScadenzaIscrizioni())) throw new IllegalArgumentException("Data inizio non valida");
        if (hackathon.getDataFine().isBefore(hackathon.getDataInizio())) throw new IllegalArgumentException("Data fine non valida");
        if (hackathon.getNumMaxTeam()<=0) throw new IllegalArgumentException("Numero massimo di team non valido");
        if (hackathon.getDimensioneMaxTeam()<=0) throw new IllegalArgumentException("Dimensione massima di team non valida");
        if (hackathon.getRegolamento()==null || hackathon.getRegolamento().isEmpty()) throw new IllegalArgumentException("Regolamento non valido");

        hackathon = repositoryHackathon.insertInto(hackathon)
                .orElseThrow(() -> new EntityNotFoundException("Errore nel salvataggio dell'hackathon"));

        //assegnazione del nuovo hackathon come hackathon corrente per tutti i membri staff
        organizzatore.setHackathon(hackathon);
        giudice.setHackathon(hackathon);
        for (MembroStaff mentore : mentori) {
            mentore.setHackathon(hackathon);
        }

        //aggiornamento delle entità nel database
        repositoryMembriStaff.updateMembroStaff(organizzatore);
        repositoryMembriStaff.updateMembroStaff(giudice);
        for (MembroStaff mentore : mentori) repositoryMembriStaff.updateMembroStaff(mentore);

        return hackathon;
    }

    //Elimina un hackathon solo se il suo stato è CONCLUSO.
    @Override
    @Transactional
    public boolean eliminaHackathon(Long idHackathon) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        if (hackathon.getStato() != StatoHackathon.CONCLUSO) {
            throw new IllegalStateException("Hackathon non concluso");
        }

        repositoryHackathon.deleteHackathonById(hackathon.getId());
        return repositoryHackathon.findHackathonById(hackathon.getId()).isEmpty();
    }

    // se l'hackathon si trova ancora in iscrizione oppure in corso aggiunta di un nuovo mentore
    @Override
    public boolean addMentore(Long idUtente, Long idHackathon) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        MembroStaff mentore = repositoryMembriStaff.findMembroStaffById(idUtente).orElseThrow(() -> new EntityNotFoundException("Mentore non presente"));
        if (!(hackathon.getStato() == StatoHackathon.IN_ISCRIZIONE || hackathon.getStato() == StatoHackathon.IN_CORSO))
            throw new RuntimeException("Hackathon non in iscrizione o in corso");
        if (mentore.getHackathon() == null) {
            hackathon.getMentori().add(mentore);
            mentore.setHackathon(hackathon);
            repositoryHackathon.updateHackathon(hackathon);
            repositoryMembriStaff.updateMembroStaff(mentore);
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public boolean espelliTeam(Long idTeam, Long idHackathon) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        Team team = repositoryTeam.findTeamById(idTeam).orElseThrow(() -> new EntityNotFoundException("Team non presente"));
        List<Segnalazione> segnalazioni = repositorySegnalazione.findAllSegnalazioniByTeamId(idTeam);
        team.getSegnalazioni().removeAll(segnalazioni);
        hackathon.getSegnalazioni().removeAll(segnalazioni);
        for (Segnalazione s : segnalazioni){
            repositorySegnalazione.deleteSegnalazione(s.getId());
        }
        hackathon.getTeams().remove(team);
        team.setHackathon(null);
        repositoryTeam.updateTeam(team);
        repositoryHackathon.updateHackathon(hackathon);
        return true;
    }

    @Override
    public Hackathon changeStato(Long idHackathon) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        switch (hackathon.getStato()) {
            case IN_ISCRIZIONE: hackathon.setStato(StatoHackathon.IN_CORSO);
            break;
            case IN_CORSO: hackathon.setStato(StatoHackathon.IN_VALUTAZIONE);
            break;
            case IN_VALUTAZIONE: hackathon.setStato(StatoHackathon.CONCLUSO);
            break;
        }
        repositoryHackathon.updateHackathon(hackathon);
        return hackathon;
    }

}
