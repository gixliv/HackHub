package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriStaff;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryUtenti;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IHackathonService;
import it.unicam.hackhub.hackhub.Application.DTO.Request.HackathonRequest;
import it.unicam.hackhub.hackhub.Core.enums.Ruolo;
import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Core.models.Team;
import it.unicam.hackhub.hackhub.Core.models.Utente;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HackathonService implements IHackathonService {

    private final IRepositoryHackathon repositoryHackathon;
    private final IRepositoryUtenti repositoryUtenti;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryMembriStaff repositoryMembriStaff;

    public HackathonService(IRepositoryHackathon repositoryHackathon, IRepositoryUtenti repositoryUtenti, IRepositoryTeam repositoryTeam, IRepositoryMembriStaff repositoryMembriStaff) {
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryUtenti = repositoryUtenti;
        this.repositoryTeam = repositoryTeam;
        this.repositoryMembriStaff = repositoryMembriStaff;
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

    /**
     * Crea un nuovo hackathon nel sistema.
     *
     * @param request         i dati dell'hackathon da creare
     * @param idOrganizzatore l'id del membro dello staff che sarà l'organizzatore
     * @return l'hackathon creato
     * @throws EntityNotFoundException se l'organizzatore non esiste
     * @throws IllegalStateException   se l'organizzatore è già assegnato a un hackathon
     * @throws EntityExistsException   se esiste già un hackathon con lo stesso nome
     */
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

        Hackathon hackathon = Hackathon.builder()
                .nome(request.getNome())
                .regolamento(request.getRegolamento())
                .scadenzaIscrizioni(request.getScadenzaIscrizioni())
                .dataInizio(request.getDataInizio())
                .dataFine(request.getDataFine())
                .luogo(request.getLuogo())
                .dimensioneMaxTeam(request.getDimensioneMaxTeam())
                .numMaxTeam(request.getNumMaxTeam())
                .stato(StatoHackathon.IN_ISCRIZIONE)
                .organizzatore(organizzatore)
                .build();

        hackathon = repositoryHackathon.insertInto(hackathon)
                .orElseThrow(() -> new EntityNotFoundException("Errore nel salvataggio dell'hackathon"));

        organizzatore.setHackathon(hackathon);

        repositoryMembriStaff.updateMembroStaff(organizzatore);

        return hackathon;
    }

    /**
     * Elimina un hackathon solo se il suo stato è {@link StatoHackathon#CONCLUSO}.
     *
     * @param idHackathon l'id dell'hackathon da eliminare
     * @return {@code true} se l'hackathon è stato eliminato, {@code false} altrimenti
     * @throws EntityNotFoundException se l'hackathon non esiste
     * @throws IllegalStateException   se l'hackathon non è concluso
     */

    @Override
    public boolean eliminaHackathon(Long idHackathon) {
        Hackathon hackathon = repositoryHackathon.findHackathonById(idHackathon).orElseThrow(() -> new EntityNotFoundException("Hackathon non presente"));
        if (hackathon.getStato() != StatoHackathon.CONCLUSO) {
            throw new IllegalStateException("Hackathon non concluso");
        }

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


}
