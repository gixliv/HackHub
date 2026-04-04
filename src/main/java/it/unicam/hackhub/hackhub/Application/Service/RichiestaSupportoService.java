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

    @Override
    @Transactional
    public RichiestaSupportoResponse inviaRichiestaSupporto(RichiestaSupportoRequest request) {
        if(request==null) throw new EntityNotFoundException();
        Team team=repositoryTeam.findTeamById(request.getTeamId()).orElseThrow(EntityNotFoundException::new);
        Hackathon hackathon=repositoryHackathon.findHackathonById(request.getHackathonId()).orElseThrow(EntityNotFoundException::new);
        if(hackathon.getStato()!= StatoHackathon.IN_CORSO) throw new EntityNotFoundException("L'hackathon non è in corso, impossibile inviare richiesta di supporto");

        MembroStaff mentore=repositoryMembriStaff.findMembroStaffById(request.getMentoreId()).orElseThrow();
        if(!mentore.getHackathon().getId().equals(hackathon.getId())) throw new IllegalArgumentException("mentore non appartenente all'hackathon");

        RichiestaSupportoMapper map= new RichiestaSupportoMapper();
        RichiestaSupporto richiestaSupporto=map.toEntity(request);
        repositoryRichiestaSupporto.insertInto(richiestaSupporto);
        repositoryRichiestaSupporto.updateRichiestaSupporto(richiestaSupporto);

        hackathon.getRichiesteSupporto().add(richiestaSupporto);
        repositoryHackathon.updateHackathon(hackathon);

        //if(repositoryRichiestaSupporto.findRichiestaSupportoById(richiestaSupporto.getId()).isPresent())
            return map.toResponse(richiestaSupporto);
        //throw new EntityNotFoundException("Richiesta di supporto non inviata");
    }

    @Override
    public List<RichiestaSupporto> getAllRichiesteSupporto(Long idHackathon) {
        if(idHackathon==null) throw new IllegalArgumentException();
        Hackathon hackathon=repositoryHackathon.findHackathonById(idHackathon).orElseThrow(EntityNotFoundException::new);
        return repositoryRichiestaSupporto.findAllRichiesteSupporto(hackathon.getId());
    }
}
