package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriStaff;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryRichiestaSupporto;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.IRichiestaSupportoService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.RichiestaSupportoMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.RichiestaSupportoRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.RichiestaSupportoResponse;
import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Core.models.RichiestaSupporto;
import it.unicam.hackhub.hackhub.Core.models.Team;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiestaSupportoService implements IRichiestaSupportoService {

    private final IRepositoryTeam repositoryTeam;
    private final IRepositoryHackathon repositoryHackathon;
    private final IRepositoryRichiestaSupporto repositoryRichiestaSupporto;
    private final IRepositoryMembriStaff repositoryMembriStaff;

    public RichiestaSupportoService(IRepositoryTeam repositoryTeam, IRepositoryHackathon repositoryHackathon, IRepositoryRichiestaSupporto repositoryRichiestaSupporto, IRepositoryMembriStaff repositoryMembriStaff) {
        this.repositoryTeam = repositoryTeam;
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryRichiestaSupporto = repositoryRichiestaSupporto;
        this.repositoryMembriStaff = repositoryMembriStaff;
    }

    //Creazione e invio di una nuova richiesta di supporto
    @Override
    @Transactional
    public RichiestaSupporto inviaRichiestaSupporto(RichiestaSupportoRequest request) {
        if(request==null) throw new EntityNotFoundException();

        //verifica se team e hackathon siano presenti, se il team è iscritto all'hackathon e se l'hackathon sia inn corso.
        Team team=repositoryTeam.findTeamById(request.getTeamId()).orElseThrow(EntityNotFoundException::new);
        Hackathon hackathon=repositoryHackathon.findHackathonById(request.getHackathonId()).orElseThrow(EntityNotFoundException::new);
        if(team.getHackathon()==null) throw new EntityNotFoundException("Il team non è inscritto all'hackathon, impossibile inviare richiesta di supporto");
        if(hackathon.getStato()!= StatoHackathon.IN_CORSO) throw new IllegalStateException("L'hackathon non è in corso, impossibile inviare richiesta di supporto");

        //verifica del mentore e della sua appartenenza all'hackathon
        MembroStaff mentore=repositoryMembriStaff.findMembroStaffById(request.getMentoreId()).orElseThrow();
        if(!mentore.getHackathon().getId().equals(hackathon.getId())) throw new IllegalArgumentException("mentore non appartenente all'hackathon");

        //creazione del mapper per l'entità
        RichiestaSupportoMapper map= new RichiestaSupportoMapper();
        RichiestaSupporto richiestaSupporto=map.toEntity(request);

        //Le entità team, mentore e hackathon vengono impostate manualmente per evitare deferenziazioni
        richiestaSupporto.setTeam(team);
        richiestaSupporto.setHackathon(hackathon);
        richiestaSupporto.setMentore(mentore);

        repositoryRichiestaSupporto.insertInto(richiestaSupporto);

        hackathon.getRichiesteSupporto().add(richiestaSupporto);
        repositoryHackathon.updateHackathon(hackathon);

        //se la richiesta di supporto è stata aggiunta con successo viene restituita
        if(repositoryRichiestaSupporto.findRichiestaSupportoById(richiestaSupporto.getId()).isPresent())
            return richiestaSupporto;
        throw new EntityNotFoundException("Richiesta di supporto non inviata");
    }

    //Lista di tutte le richieste di supporto di un hackathon
    @Override
    public List<RichiestaSupporto> getAllRichiesteSupporto(Long idHackathon) {
        if(idHackathon==null) throw new IllegalArgumentException();
        Hackathon hackathon=repositoryHackathon.findHackathonById(idHackathon).orElseThrow(EntityNotFoundException::new);
        return repositoryRichiestaSupporto.findAllRichiesteSupporto(hackathon.getId());
    }
}
