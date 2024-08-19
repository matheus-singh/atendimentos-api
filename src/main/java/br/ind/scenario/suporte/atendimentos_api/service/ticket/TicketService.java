package br.ind.scenario.suporte.atendimentos_api.service.ticket;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.dto.TicketDTO;
import br.ind.scenario.suporte.atendimentos_api.service.repository.TicketRepository;
import br.ind.scenario.suporte.atendimentos_api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    private TicketDTO convertTicketToTicketDTO(Ticket ticket){
        return new TicketDTO(ticket);
    }

    public TicketDTO getTicketByNumber(Integer number){
        return convertTicketToTicketDTO(ticketRepository.findByNumber(number));
    }

    public List<TicketDTO> getTicketsByDate(String date) {
        List<Ticket> tickets = ticketRepository.findByDate(DateUtils.createLocalDateFromString(date));
        return tickets.stream().map(TicketDTO::new).collect(Collectors.toList());
    }

    public List<TicketDTO> getTicketsOfTheWeek() {
        List<Ticket> tickets = ticketRepository.findByDate(DateUtils.getLocalDateOfTheWeek());
        return tickets.stream().map(TicketDTO::new).collect(Collectors.toList());
    }
}
