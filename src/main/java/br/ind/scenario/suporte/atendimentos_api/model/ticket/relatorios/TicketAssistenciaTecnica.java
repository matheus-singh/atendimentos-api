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
@DiscriminatorValue("assistencia tecnica")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketAssistenciaTecnica extends Ticket {
    private String produtosOuServicos;
    private String numeroDeSerie;
    private String macAdress;
    private String versaoFirmwareAcModulos;
    private String emGarantia;

    @Override
    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "assistencia tecnica";
    }

    public TicketAssistenciaTecnica (TicketSearchData ticketSearchData) {
        super(ticketSearchData);

        // Setando atributos específicos de sugestão
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
        this.setNumeroDeSerie(ticketSearchData.customFieldData().numeroDeSerie());
        this.setMacAdress(ticketSearchData.customFieldData().macAdress());
        this.setVersaoFirmwareAcModulos(ticketSearchData.customFieldData().versaoFirmwareAcModulos());
        this.setEmGarantia(ticketSearchData.customFieldData().emGarantia());
    }

    public void update(TicketAssistenciaTecnica newTicket) {
        super.update(newTicket);

        // Setando atributos específicos de sugestão
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
        this.setNumeroDeSerie(newTicket.getNumeroDeSerie());
        this.setMacAdress(newTicket.getMacAdress());
        this.setVersaoFirmwareAcModulos(newTicket.getVersaoFirmwareAcModulos());
        this.setEmGarantia(newTicket.getEmGarantia());
    }
}
