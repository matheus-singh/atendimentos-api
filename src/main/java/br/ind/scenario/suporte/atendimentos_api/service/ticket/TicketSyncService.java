package br.ind.scenario.suporte.atendimentos_api.service.ticket;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.service.data.IDataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.repository.TicketRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketSyncService {

    @Autowired
    private IDataConverter dataConverter;

    @Autowired
    private ConsumoOctadeskAPI octadeskAPI;

    @Autowired
    private TicketRepository ticketRepository;

    private static final Logger logger = LoggerFactory.getLogger(TicketSyncService.class);

    @PostConstruct
    public void init() {
        saveTicketsOfTheWeek();
    }

    private void updateExistingTicket(Ticket existingTicket, Ticket newTicket) {
        existingTicket.setNumero(newTicket.getNumero());
        existingTicket.setStatus(newTicket.getStatus());
        existingTicket.setClassificacao(newTicket.getClassificacao());
        existingTicket.setTitulo(newTicket.getTitulo());
        existingTicket.setRevenda(newTicket.getRevenda());
        existingTicket.setTecnico(newTicket.getTecnico());
        existingTicket.setEmailDoTecnico(newTicket.getEmailDoTecnico());
        existingTicket.setConsultor(newTicket.getConsultor());
        existingTicket.setEmailConsultor(newTicket.getEmailConsultor());
        existingTicket.setDataDeCriacao(newTicket.getDataDeCriacao());
        existingTicket.setDataDeResolucao(newTicket.getDataDeResolucao());
        existingTicket.setLinkOD(newTicket.getLinkOD());
        existingTicket.setProduto(newTicket.getProduto());
        existingTicket.setLinhaDeProduto(newTicket.getLinhaDeProduto());
    }

    private void createTicketsAndSaveAll (String listOfTicketsJson, TicketRepository repository){
        try {
            logger.info("Creating Tickets...");
            List<TicketSearchData> ticketsData = dataConverter.stringToJsonList(listOfTicketsJson, TicketSearchData.class);
            List<Ticket> tickets = ticketsData.stream().map(Ticket::new).collect(Collectors.toList());
            logger.info("Total of {} Tickets Created. Saving and updating the Data Base...", tickets.size());

            tickets.forEach(ticket -> {
                Optional<Ticket> existingTicketOpt = repository.findByOctaId(ticket.getOctaId());
                if (existingTicketOpt.isPresent()) {
                    Ticket existingTicket = existingTicketOpt.get();
                    updateExistingTicket(existingTicket, ticket);
                    repository.save(existingTicket);
                } else {
                    repository.save(ticket);
                }
            });
            logger.info("Tickets Saved and Updated. Returning...");
        } catch (Exception e) {
            logger.info("An error has occurred: {}", e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 6,19 * * *")
    public void saveTicketsOfTheWeek(){
        try {
            logger.info("Starting sync..");
            String listOfTicketsJson = octadeskAPI.getTicketsOfTheWeek();
            this.createTicketsAndSaveAll(listOfTicketsJson, ticketRepository);
            logger.info("Database sync completed.");
        } catch (Exception e) {
            System.err.println("Error during database feed: " + e);
        }
    }
}
