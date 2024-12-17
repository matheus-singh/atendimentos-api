package br.ind.scenario.suporte.atendimentos_api.service.ticket;

import br.ind.scenario.suporte.atendimentos_api.model.dto.TicketDTO;
import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.model.ticket.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.ticket.TicketFactory;
import br.ind.scenario.suporte.atendimentos_api.service.data.IDataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.repository.TicketRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketSyncService {

    @Autowired
    private IDataConverter dataConverter;

    @Autowired
    private ConsumoOctadeskAPI octadeskAPI;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketFactory ticketFactory;

    private static final Logger logger = LoggerFactory.getLogger(TicketSyncService.class);

    // Este método será chamado ao iniciar o aplicativo para sincronizar tickets
    @PostConstruct
    public void init() {
        syncDailyTickets();
    }

    private void logError(Exception e) {
        logger.info("An error has occurred: {}", e+": "+ e.getMessage());
    }

    private TicketDTO convertTicketToTicketDTO(Ticket ticket){
        return new TicketDTO(ticket);
    }

    private void saveOrUpdateTicket(Ticket ticket) {
        Optional<Ticket> existingTicketOpt = Optional.ofNullable(ticketRepository.findByNumber(ticket.getNumero()));
        if (existingTicketOpt.isPresent() && !(existingTicketOpt.get().getOctaId() == null)) {
            Ticket existingTicket = existingTicketOpt.get();
            if(!Objects.equals(existingTicket.getRelatorio(), ticket.getRelatorio())){
                ticketRepository.delete(existingTicket);
                ticketRepository.save(ticket);
            } else {
                existingTicket = ticketFactory.updateTicket(existingTicket, ticket);
                ticketRepository.save(existingTicket);
            }
        } else {
            if (!(ticket.getOctaId() == null)) {
                ticketRepository.save(ticket);
            }
        }
    }

    private void saveListOfTickets(List<Ticket> tickets){
        try {
            tickets.forEach(this::saveOrUpdateTicket);
            logger.info("Tickets sincronizados!");
        } catch (Exception e) {
            logger.info("<--------------------- Erro na sincronização do banco de dados --------------------->");
            logError(e);
        }
    }

    private List<Ticket> getTicketsFromOctaByNumberRange(Long primeiroNumero, Long ultimoNumero){
        List<Ticket> listaDeTicketsEncontrados = new ArrayList<>();
        try {
            logger.info("Sincronizando tickets...");
            for (long i = primeiroNumero; i <= ultimoNumero; i++) {
                String ticketJson = octadeskAPI.getTicket(i);
                TicketSearchData ticketFound = dataConverter.stringToJsonObject(ticketJson, TicketSearchData.class);
                Optional<Ticket> ticketOptional = Optional.ofNullable(ticketFactory.create(ticketFound));
                ticketOptional.ifPresent(listaDeTicketsEncontrados::add);
            }
        } catch (NullPointerException e){
            logger.info("<--------------------- Ticket não encontrado! Sincronização finalizada!--------------------->");
        } catch (Exception e) {
            logger.info("<--------------------- Erro na requisição dos tickets para sincronização --------------------->");
            logError(e);
        }
        return listaDeTicketsEncontrados;
    }

    public TicketDTO saveTicketFromOcta(Long number) {
        String ticketJson = octadeskAPI.getTicket(number);
        TicketSearchData ticketData = dataConverter.stringToJsonObject(ticketJson, TicketSearchData.class);
        Optional<Ticket> ticket = Optional.ofNullable(ticketFactory.create(ticketData));
        if(ticket.isPresent()){
            Ticket ticketToSave = ticket.get();
            saveOrUpdateTicket(ticketToSave);
            return this.convertTicketToTicketDTO(ticketToSave);
        }
        return null;
    }

    // Método para sincronizar os tickets do dia, a cada 2 minutos
    @Scheduled(cron = "0 0/2 * * * ?")
    public void syncDailyTickets() {
        Long ultimoNumero = Long.MIN_VALUE;

        Optional<Ticket> youngestTicketOp = ticketRepository.getUltimoTicket();
        if (youngestTicketOp.isPresent()){
            Ticket ultimoTicket = youngestTicketOp.get();
            ultimoNumero = ultimoTicket.getNumero();
        } else {
            ultimoNumero = 21300L;
        }

        List<Ticket> listaDeTicketsEncontrados =
                getTicketsFromOctaByNumberRange(ultimoNumero-100, ultimoNumero+50);
        saveListOfTickets(listaDeTicketsEncontrados);
    }

    // Método para sincronizar os tickets da semana, a cada 12 horas
    @Scheduled(cron = "0 0 12/12 * * ?")
    public void syncWeekTickets() {
        Long ultimoNumero = Long.MIN_VALUE;

        Optional<Ticket> youngestTicketOp = ticketRepository.getUltimoTicket();
        if (youngestTicketOp.isPresent()){
            Ticket ultimoTicket = youngestTicketOp.get();
            ultimoNumero = ultimoTicket.getNumero();
        } else {
            ultimoNumero = 21300L;
        }

        List<Ticket> listaDeTicketsEncontrados =
                getTicketsFromOctaByNumberRange(ultimoNumero-300, ultimoNumero+50);
        saveListOfTickets(listaDeTicketsEncontrados);
    }
}
