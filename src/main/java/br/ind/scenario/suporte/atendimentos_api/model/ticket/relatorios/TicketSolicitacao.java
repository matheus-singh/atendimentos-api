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
@DiscriminatorValue("solicitacao")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketSolicitacao extends Ticket {
    private String categoria;
    private String produtosOuServicos;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "solicitacao";
    }

    public TicketSolicitacao(TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setCategoria(ticketSearchData.customFieldData().categoria());
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
    }

    public void update(TicketSolicitacao newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setCategoria(newTicket.getCategoria());
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
    }
}
