package br.ind.scenario.suporte.atendimentos_api.model.ticket.relatorios;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.model.ticket.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("novo")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketNovo extends Ticket {
    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "novo";
    }

    public TicketNovo (TicketSearchData ticketSearchData) {
        super(ticketSearchData);
    }

    public void update(TicketNovo newTicket) {
        super.update(newTicket);
    }
}
