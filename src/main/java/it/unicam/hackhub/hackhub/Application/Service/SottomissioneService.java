package it.unicam.hackhub.hackhub.Application.Service;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryHackathon;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositorySottomissione;
import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryTeam;
import it.unicam.hackhub.hackhub.Application.Abstraction.Service.ISottomissioneService;
import it.unicam.hackhub.hackhub.Application.DTO.Mapper.SottomissioneMapper;
import it.unicam.hackhub.hackhub.Application.DTO.Request.SottomissioneRequest;
import it.unicam.hackhub.hackhub.Application.DTO.Response.SottomissioneResponse;
import it.unicam.hackhub.hackhub.Core.enums.StatoHackathon;
import it.unicam.hackhub.hackhub.Core.models.Hackathon;
import it.unicam.hackhub.hackhub.Core.models.Sottomissione;
import it.unicam.hackhub.hackhub.Core.models.Team;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SottomissioneService implements ISottomissioneService {

    private final IRepositoryHackathon repositoryHackathon;
    private final IRepositoryTeam repositoryTeam;
    private final IRepositorySottomissione repositorySottomissione;

    public SottomissioneService(IRepositoryHackathon repositoryHackathon, IRepositoryTeam repositoryTeam, IRepositorySottomissione repositorySottomissione) {
        this.repositoryHackathon = repositoryHackathon;
        this.repositoryTeam = repositoryTeam;
        this.repositorySottomissione = repositorySottomissione;
    }

    //Viene creata una nuova sottomissione e aggiunta al team solo se il team non ne aveva già create altre
    //la sottomissione viene aggiunta all'hackathon solo se l'hackathon è in corso
    @Override
    public SottomissioneResponse inviaSottomissione(SottomissioneRequest request) {
        if(request==null) return null;
        Team team=repositoryTeam.findTeamById(request.getIdTeam()).orElseThrow(EntityNotFoundException::new);
        Hackathon hackathon=repositoryHackathon.findHackathonById(request.getIdHackathon()).orElseThrow(EntityNotFoundException::new);

        if(!(hackathon.getStato().equals(StatoHackathon.IN_CORSO))) throw new RuntimeException("L'hackathon non è in corso");
        if(repositorySottomissione.findSottomissioneByTeamId(team.getId()).isPresent()) throw new RuntimeException("Sottomissione già esistente, effettuare un aggiornamento se necessario");

        SottomissioneMapper mapper= new SottomissioneMapper();
        Sottomissione sottomissione =mapper.toEntity(request);
        repositorySottomissione.insertInto(sottomissione);

        team.setSottomissione(sottomissione);
        repositoryTeam.updateTeam(team);

        hackathon.getSottomissioni().add(sottomissione);
        repositoryHackathon.updateHackathon(hackathon);

        return mapper.toResponse(sottomissione);
    }

    //Una sottomissione può essere aggiornata in caso di modifiche al linkRepository, alla descrizione o al titolo
    //Quando un team effettua modifiche ai file presenti nel linkRepository non è necessario un aggiornamento, i file si autoaggiornano ad ogni modifica
    @Override
    public SottomissioneResponse aggiornaSottomissione(Long idSottomissione, SottomissioneRequest request) {
        if(request==null) return null;
        Sottomissione sottomissione=repositorySottomissione.findSottomissioneById(idSottomissione).orElseThrow(() -> new EntityNotFoundException("Sottomissione non presente, impossibile aggiornare"));
        if(request.getDescrizione()== null) request.setDescrizione(sottomissione.getDescrizione());
        if(request.getTitolo()== null) request.setTitolo(sottomissione.getTitolo());
        if(request.getLinkRepository()==null) request.setLinkRepository(sottomissione.getLinkRepository());
        request.setIdTeam(sottomissione.getTeam().getId());
        SottomissioneMapper mapper= new SottomissioneMapper();
        sottomissione= mapper.toEntity(request);
        repositorySottomissione.updateSottomissione(sottomissione);
        return mapper.toResponse(sottomissione);
    }

    //La lista di tutte le sottomissioni presenti in uno specifico hackathon può essere richiesta dal giudice
    @Override
    public List<Sottomissione> getAllSottomissioni(Long idHackathon) {
        return repositorySottomissione.findAllSottomissioni(idHackathon);
    }
}
