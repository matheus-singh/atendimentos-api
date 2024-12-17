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
@DiscriminatorValue("em analise")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketEmAnalise extends Ticket {
    private String timeResponsavelPelaAnalise;
    private String natureza;
    private String produtosOuServicos;
    private String instalacao;
    private String problemaDeProduto;
    private String classificacaoDev;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "em analise";
    }

    public TicketEmAnalise (TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setTimeResponsavelPelaAnalise(ticketSearchData.customFieldData().timeResponsavelPelaAnalise());
        this.setNatureza(ticketSearchData.customFieldData().natureza());
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
        this.setInstalacao(ticketSearchData.customFieldData().instalacao());
        this.setProblemaDeProduto(ticketSearchData.customFieldData().problemaDeProduto());
        this.setClassificacaoDev(ticketSearchData.customFieldData().classificacaoDev());
    }

    public void update(TicketEmAnalise newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setTimeResponsavelPelaAnalise(newTicket.getTimeResponsavelPelaAnalise());
        this.setNatureza(newTicket.getNatureza());
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
        this.setInstalacao(newTicket.getInstalacao());
        this.setProblemaDeProduto(newTicket.getProblemaDeProduto());
        this.setClassificacaoDev(newTicket.getClassificacaoDev());
    }
}
