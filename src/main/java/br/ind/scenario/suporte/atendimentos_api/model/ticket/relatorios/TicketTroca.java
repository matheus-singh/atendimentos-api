package br.ind.scenario.suporte.atendimentos_api.model.ticket.relatorios;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.model.ticket.Ticket;
import br.ind.scenario.suporte.atendimentos_api.util.DateTimeUtils;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("troca")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketTroca extends Ticket {
    private String produtosOuServicos;
    private String numeroDeSerie;
    private String macAdress;
    private String emGarantia;
    private String instalacao;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "troca";
    }

    public TicketTroca(TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
        this.setNumeroDeSerie(ticketSearchData.customFieldData().numeroDeSerie());
        this.setMacAdress(ticketSearchData.customFieldData().macAdress());
        this.setEmGarantia(ticketSearchData.customFieldData().emGarantia());
        this.setInstalacao(ticketSearchData.customFieldData().instalacao());
    }

    public void update(TicketTroca newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
        this.setNumeroDeSerie(newTicket.getNumeroDeSerie());
        this.setMacAdress(newTicket.getMacAdress());
        this.setEmGarantia(newTicket.getEmGarantia());
        this.setInstalacao(newTicket.getInstalacao());
    }
}
