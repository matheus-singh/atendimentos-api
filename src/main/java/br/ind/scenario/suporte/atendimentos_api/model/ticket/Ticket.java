package br.ind.scenario.suporte.atendimentos_api.model.ticket;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Tickets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "relatorio",
        discriminatorType = DiscriminatorType.STRING
)
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String octaId;
    private Long numero;
    private String status;
    private String titulo;
    private String revenda;
    private String tecnico;
    private String consultor;
    private LocalDate dataDeCriacao;
    private LocalDate dataDeResolucao;
    private LocalTime horaDeCriacao;
    private LocalTime horaDeResolucao;
    private String linkOD;
    private String linhaDeProduto;
    private String numeroDoTicket;
    private String mes;
    private String ano;

    public abstract String getRelatorio();

    public Ticket (TicketSearchData ticketSearchData){
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
        this.setNumeroDoTicket(String.valueOf(ticketSearchData.numero()));
        this.setMes(DateTimeUtils.createLocalDateFromString(ticketSearchData.dataDeCriacao()).format(DateTimeFormatter.ofPattern("MMMM")));
        this.setAno(String.valueOf(DateTimeUtils.createLocalDateFromString(ticketSearchData.dataDeCriacao()).getYear()));
    }

    public void update(Ticket newTicket){
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
        this.setNumeroDoTicket(newTicket.getNumeroDoTicket());
        this.setMes(newTicket.getMes());
        this.setAno(newTicket.getAno());
    }
}
