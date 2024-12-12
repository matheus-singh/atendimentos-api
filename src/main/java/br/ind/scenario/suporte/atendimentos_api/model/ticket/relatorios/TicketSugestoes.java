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
@DiscriminatorValue("sugestao")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketSugestoes extends Ticket {
    private String natureza;
    private String produtosOuServicos;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "sugestao";
    }

    public TicketSugestoes (TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setNatureza(ticketSearchData.customFieldData().natureza());
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
    }

    public void update(TicketSugestoes newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setNatureza(newTicket.getNatureza());
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
    }
}
