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
@DiscriminatorValue("agendamento")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketAgendamento extends Ticket {
    private String tipoDeAgendamento;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "agendamento";
    }

    public TicketAgendamento(TicketSearchData ticketSearchData){
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setTipoDeAgendamento(ticketSearchData.customFieldData().tipoDeAgendamento());
    }

    public void update(TicketAgendamento newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setTipoDeAgendamento(newTicket.getTipoDeAgendamento());
    }
}
