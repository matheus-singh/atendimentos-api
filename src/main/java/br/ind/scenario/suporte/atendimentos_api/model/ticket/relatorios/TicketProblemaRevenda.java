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
@DiscriminatorValue("problema revenda")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketProblemaRevenda extends Ticket {
    private String natureza;
    private String produtosOuServicos;
    private String causaDoProblema;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "problema revenda";
    }

    public TicketProblemaRevenda(TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setNatureza(ticketSearchData.customFieldData().natureza());
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
        this.setCausaDoProblema(ticketSearchData.customFieldData().causaDoProblema());
    }

    public void update(TicketProblemaRevenda newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setNatureza(newTicket.getNatureza());
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
        this.setCausaDoProblema(newTicket.getCausaDoProblema());
    }
}
