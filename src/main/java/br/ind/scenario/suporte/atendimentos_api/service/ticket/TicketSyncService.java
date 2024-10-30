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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketSyncService {

    @Autowired
    private IDataConverter dataConverter;

    @Autowired
    private ConsumoOctadeskAPI octadeskAPI;

    @Autowired
    private TicketRepository ticketRepository;

    private static final Logger logger = LoggerFactory.getLogger(TicketSyncService.class);

    // Este método será chamado ao iniciar o aplicativo para sincronizar tickets
    @PostConstruct
    public void init() {
        syncTickets();
    }

    // Método para sincronizar os ultimos tickets de 30 em 30 minutos, segunda a sexta, das 08:00 às 18:00
    @Scheduled(cron = "0 0/30 8-18 * * MON-FRI")
    public void syncTickets() {
        Optional<Ticket> optUltimoTicket = ticketRepository.getUltimoTicket();
        Long numero = Long.MIN_VALUE;
        Ticket ultimoTicket = new Ticket();
        if (optUltimoTicket.isPresent()){
            ultimoTicket = optUltimoTicket.get();
            numero = ultimoTicket.getNumero();
        } else {
            numero = 11L;
        }
        List<Ticket> listaDeTicketsEncontrados = new ArrayList<>();
        try {
            logger.info("Sincronizando tickets...");
            for (long i = numero - 10; i <= numero + 10; i++) {
                String ticketJson = octadeskAPI.getTicket(i);
                TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
                Ticket ticket = new Ticket(ticketFound);
                listaDeTicketsEncontrados.add(ticket);
            }
        } catch (Exception e) {
            logger.info("<--------------------- Erro na requisição dos tickets para sincronização --------------------->");
            logError(e);
        }
        try {
            listaDeTicketsEncontrados.forEach(this::saveOrUpdateTicket);
            logger.info("Tickets sincronizados!");
        } catch (Exception e) {
            logger.info("<--------------------- Erro na sincronização do banco de dados --------------------->");
            logError(e);
        }
    }

    // Método para sincronizar todos os tickets todo sábado às 00:00 horas
    @Scheduled(cron = "0 0 0 * * SAT")
    public void syncAllTimeTickets() {
        Optional<Ticket> optUltimoTicket = ticketRepository.getUltimoTicket();
        Long numero = Long.MIN_VALUE;
        Ticket ultimoTicket = new Ticket();
        if (optUltimoTicket.isPresent()){
            ultimoTicket = optUltimoTicket.get();
            numero = ultimoTicket.getNumero();
        } else {
            numero = 11L;
        }
        List<Ticket> listaDeTicketsEncontrados = new ArrayList<>();
        int counter = 0;
        try {
            logger.info("Sincronizando todos os tickets...");
            for (long i = 1L; i <= 20000L; i++) {
                String ticketJson = octadeskAPI.getTicket(i);
                TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
                Ticket ticket = new Ticket(ticketFound);
                listaDeTicketsEncontrados.add(ticket);
                counter++;
                if (counter == 500) {
                    try {
                        listaDeTicketsEncontrados.forEach(this::saveOrUpdateTicket);
                        logger.info("500 Tickets Salvos e Atualizados.");
                    } catch (Exception e) {
                        logger.info("<--------------------- Erro na sincronização dos 500 tickets no banco de dados --------------------->");
                        logError(e);
                    }
                    counter = 0;
                    listaDeTicketsEncontrados.clear();
                }
            }
        } catch (Exception e) {
            logger.info("<--------------------- Erro na sincronização de todos os tickets --------------------->");
            logError(e);
        }
        if (!listaDeTicketsEncontrados.isEmpty()) {
            try {
                listaDeTicketsEncontrados.forEach(this::saveOrUpdateTicket);
                logger.info("Tickets Restantes Salvos e Atualizados.");
            } catch (Exception e) {
                logger.info("<--------------------- Erro na sincronização dos tickets restantes --------------------->");
                logError(e);
            }
        }
    }

    // Demais métodos auxiliares
    public void updateExistingTicket(Ticket existingTicket, Ticket newTicket) {
        existingTicket.setNumero(newTicket.getNumero());
        existingTicket.setStatus(newTicket.getStatus());
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
        existingTicket.setTopico(newTicket.getTopico());
        existingTicket.setClassificacaoDoTopico(newTicket.getClassificacaoDoTopico());
        existingTicket.setNumeroDeSerie(newTicket.getNumeroDeSerie());
    }

    private void saveOrUpdateTicket(Ticket ticket) {
        Optional<Ticket> existingTicketOpt = Optional.ofNullable(ticketRepository.findByNumber(ticket.getNumero()));
        if (existingTicketOpt.isPresent() && !(existingTicketOpt.get().getOctaId() == null)) {
            Ticket existingTicket = existingTicketOpt.get();
            updateExistingTicket(existingTicket, ticket);
            ticketRepository.save(existingTicket);
        } else {
            if (!(ticket.getOctaId() == null)) {
                ticketRepository.save(ticket);
            }
        }
    }

    private void logError(Exception e) {
        logger.info("An error has occurred: {}", e.getMessage());
    }

    // Método para sincronizar os tickets da semana, todas as segundas 10:00
    @Scheduled(cron = "0 0 10 * * MON")
    public void syncWeekTickets() {
        Optional<Ticket> optUltimoTicket = ticketRepository.getUltimoTicket();
        Long numero;
        Ticket ultimoTicket;
        if (optUltimoTicket.isPresent()){
            ultimoTicket = optUltimoTicket.get();
            numero = ultimoTicket.getNumero();
        } else {
            numero = 11L;
        }
        List<Ticket> listaDeTicketsEncontrados = new ArrayList<>();
        try {
            logger.info("Sincronizando tickets...");
            for (long i = numero - 300; i <= numero + 100; i++) {
                String ticketJson = octadeskAPI.getTicket(i);
                TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
                Ticket ticket = new Ticket(ticketFound);
                listaDeTicketsEncontrados.add(ticket);
            }
        } catch (Exception e) {
            logger.info("<--------------------- Erro na requisição dos tickets para sincronização --------------------->");
            logError(e);
        }
        try {
            listaDeTicketsEncontrados.forEach(this::saveOrUpdateTicket);
            logger.info("Tickets sincronizados!");
        } catch (Exception e) {
            logger.info("<--------------------- Erro na sincronização do banco de dados --------------------->");
            logError(e);
        }
    }
}
