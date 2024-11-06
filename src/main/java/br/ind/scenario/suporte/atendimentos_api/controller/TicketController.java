package br.ind.scenario.suporte.atendimentos_api.controller;

import br.ind.scenario.suporte.atendimentos_api.model.dto.TicketDTO;
import br.ind.scenario.suporte.atendimentos_api.service.ticket.TicketService;
import br.ind.scenario.suporte.atendimentos_api.service.ticket.TicketSyncService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketSyncService ticketSyncService;

    @GetMapping("/{number}")
    public TicketDTO getTicket(@PathVariable Long number){
        return ticketService.getTicketByNumber(number);
    }

    @GetMapping("/octa/{number}")
    public String octaGetTicket(@PathVariable Long number){
        return ticketService.octaGetTicketByNumber(number);
    }

    @PutMapping("/octa/save/{number}")
    public TicketDTO saveTicketFromOcta(@PathVariable Long number){
        return ticketSyncService.saveTicketFromOcta(number);
    }

    @GetMapping("/last")
    public TicketDTO getLastTicket(){
        return ticketService.getLastTicket();
    }

    // Formato de data correto: YYYY-MM-DD
    @GetMapping("/data/{date}")
    public List<TicketDTO> getTicketsFilteringByDate(@PathVariable String date){
        return ticketService.getTicketsByDate(date);
    }

    @GetMapping("/semana")
    public List<TicketDTO> getTicketsOfTheWeek(){
        return ticketService.getTicketsOfTheWeek();
    }

    @PutMapping("/sync")
    @Transactional
    public void syncLastTickets(){
        ticketSyncService.syncTickets();
    }

    @PutMapping("/sync/all")
    @Transactional
    public void syncAllTickets(){
        ticketSyncService.syncAllTimeTickets();
    }

    @PutMapping("/sync/week")
    @Transactional
    public void syncWeekTickets(){
        ticketSyncService.syncWeekTickets();
    }
}
