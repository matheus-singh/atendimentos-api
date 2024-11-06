package br.ind.scenario.suporte.atendimentos_api.service.ticket;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.dto.TicketDTO;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.repository.TicketRepository;
import br.ind.scenario.suporte.atendimentos_api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ConsumoOctadeskAPI octadeskAPI;

    private TicketDTO convertTicketToTicketDTO(Ticket ticket){
        return new TicketDTO(ticket);
    }

    public TicketDTO getTicketByNumber(Long number){
        Optional<Ticket> ticket = Optional.ofNullable(ticketRepository.findByNumber(number));
        return ticket.map(this::convertTicketToTicketDTO).orElse(null); // Retorna null se o ticket não for encontrado
    }

    public List<TicketDTO> getTicketsByDate(String date) {
        List<Ticket> tickets = Optional.ofNullable(ticketRepository.findByDate(DateUtils.createLocalDateFromString(date)))
                .orElse(new ArrayList<>()); // Retorna uma lista vazia se for nulo
        return tickets.stream().map(TicketDTO::new).collect(Collectors.toList());
    }

    public List<TicketDTO> getTicketsOfTheWeek() {
        List<Ticket> tickets = Optional.ofNullable(ticketRepository.findByDate(DateUtils.getLocalDateOfTheWeek()))
                .orElse(new ArrayList<>()); // Retorna uma lista vazia se for nulo
        return tickets.stream().map(TicketDTO::new).collect(Collectors.toList());
    }

    public TicketDTO getLastTicket() {
        Optional<Ticket> optUltimoTicket = ticketRepository.getUltimoTicket();
        Ticket ultimoTicket = new Ticket();
        if (optUltimoTicket.isPresent()){
            ultimoTicket = optUltimoTicket.get();
        }
        return convertTicketToTicketDTO(ultimoTicket);
    }

    public String octaGetTicketByNumber(Long number) {
        String ticketJson = octadeskAPI.getTicket(number);
        return ticketJson;
    }
}
