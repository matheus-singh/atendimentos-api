package br.ind.scenario.suporte.atendimentos_api.model.ticket;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.model.ticket.relatorios.TicketSugestoes;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket create(TicketSearchData ticketSearchData){
        String relatorio = ticketSearchData.customFieldData().relatorio();
        switch(relatorio) {
            case "Sugestões":
                TicketSugestoes ticket = new TicketSugestoes(ticketSearchData);
                return ticket;
            default:
                return null;
        }
    }
}
