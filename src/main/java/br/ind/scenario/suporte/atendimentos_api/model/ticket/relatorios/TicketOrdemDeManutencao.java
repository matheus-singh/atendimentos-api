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
@DiscriminatorValue("ordem de manutencao")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketOrdemDeManutencao extends Ticket {
    private String etapaDoProcessoDeManutencao;
    private Long notaFiscal;
    private String produtosOuServicos;
    private String numeroDeSerie;
    private String macAdress;
    private String emGarantia;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "ordem de manutencao";
    }

    public TicketOrdemDeManutencao(TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setEtapaDoProcessoDeManutencao(ticketSearchData.customFieldData().etapaDoProcessoDeManutencao());
        this.setNotaFiscal(ticketSearchData.customFieldData().notaFiscal());
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
        this.setNumeroDeSerie(ticketSearchData.customFieldData().numeroDeSerie());
        this.setMacAdress(ticketSearchData.customFieldData().macAdress());
        this.setEmGarantia(ticketSearchData.customFieldData().emGarantia());
    }

    public void update(TicketOrdemDeManutencao newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setEtapaDoProcessoDeManutencao(newTicket.getEtapaDoProcessoDeManutencao());
        this.setNotaFiscal(newTicket.getNotaFiscal());
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
        this.setNumeroDeSerie(newTicket.getNumeroDeSerie());
        this.setMacAdress(newTicket.getMacAdress());
        this.setEmGarantia(newTicket.getEmGarantia());
    }
}
