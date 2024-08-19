package br.ind.scenario.suporte.atendimentos_api.controller;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.dto.TicketDTO;
import br.ind.scenario.suporte.atendimentos_api.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{number}")
    public TicketDTO getTicket(@PathVariable Integer number){
        return ticketService.getTicketByNumber(number);
    }

    @GetMapping("/data/{date}")
    public List<TicketDTO> getTicketsFilteringByDate(@PathVariable String date){
        return ticketService.getTicketsByDate(date);
    }

    @GetMapping("/semana")
    public List<TicketDTO> getTicketsOfTheWeek(){
        return ticketService.getTicketsOfTheWeek();
    }
}
