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
@DiscriminatorValue("duvida")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketDuvida extends Ticket {
    private String classificacao;
    private String natureza;
    private String produtosOuServicos;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "duvida";
    }

    public TicketDuvida (TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setNatureza(ticketSearchData.customFieldData().natureza());
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
        this.setClassificacao(ticketSearchData.customFieldData().classificacao());
    }

    public void update(TicketDuvida newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setNatureza(newTicket.getNatureza());
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
        this.setClassificacao(newTicket.getClassificacao());
    }
}
