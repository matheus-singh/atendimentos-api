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
import org.antlr.v4.runtime.misc.NotNull;

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

    @Column(name = "relatorio", insertable = false, updatable = false)
    public String getRelatorio() {
        return "duvida";
    }

    public TicketDuvida (TicketSearchData ticketSearchData) {
        // Setando atributos do Ticket
        this.setOctaId(ticketSearchData.octaId());
        this.setNumero(ticketSearchData.numero());
        this.setStatus(ticketSearchData.status());
        this.setTitulo(ticketSearchData.titulo());
        this.setRevenda(ticketSearchData.revenda());
        this.setTecnico(ticketSearchData.tecnico());
        this.setConsultor(ticketSearchData.consultor());
        this.setDataDeCriacao(DateTimeUtils.createLocalDateFromString(ticketSearchData.dataDeCriacao()));
        this.setDataDeResolucao(DateTimeUtils.createLocalDateFromString(ticketSearchData.dataDeResolucao()));
        this.setHoraDeCriacao(DateTimeUtils.createLocalTimeFromString(ticketSearchData.dataDeCriacao()));
        this.setHoraDeResolucao(DateTimeUtils.createLocalTimeFromString(ticketSearchData.dataDeResolucao()));
        this.setLinkOD(ticketSearchData.linkOD());
        this.setLinhaDeProduto(ticketSearchData.customFieldData().linhaDeProduto());

        // Setando atributos específicos de sugestão
        this.setNatureza(ticketSearchData.customFieldData().natureza());
        this.setProdutosOuServicos(ticketSearchData.produtosOuServicos());
        this.setClassificacao(ticketSearchData.customFieldData().classificacao());
    }

    public void update(TicketDuvida newTicket) {
        // Setando atributos do Ticket
        this.setNumero(newTicket.getNumero());
        this.setStatus(newTicket.getStatus());
        this.setTitulo(newTicket.getTitulo());
        this.setRevenda(newTicket.getRevenda());
        this.setTecnico(newTicket.getTecnico());
        this.setConsultor(newTicket.getConsultor());
        this.setDataDeCriacao(newTicket.getDataDeCriacao());
        this.setDataDeResolucao(newTicket.getDataDeResolucao());
        this.setHoraDeCriacao(newTicket.getHoraDeCriacao());
        this.setHoraDeResolucao(newTicket.getHoraDeResolucao());
        this.setLinkOD(newTicket.getLinkOD());
        this.setLinhaDeProduto(newTicket.getLinhaDeProduto());

        // Setando atributos específicos de sugestão
        this.setNatureza(newTicket.getNatureza());
        this.setProdutosOuServicos(newTicket.getProdutosOuServicos());
        this.setClassificacao(newTicket.getClassificacao());
    }
}
