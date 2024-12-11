package br.ind.scenario.suporte.atendimentos_api.model.dto;

import br.ind.scenario.suporte.atendimentos_api.model.ticket.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TicketDTO {

    private Long number;
    private String status;
    private String titulo;
    private String revenda;
    private String tecnico;
    private String consultor;
    private LocalDate dataDeCriacao;
    private LocalDate dataDeResolucao;
    private String linkOD;
    private String linhaDeProduto;

    public TicketDTO(Ticket ticket){
        this.number = ticket.getNumero();
        this.status = ticket.getStatus();
        this.titulo = ticket.getTitulo();
        this.revenda = ticket.getRevenda();
        this.tecnico = ticket.getTecnico();
        this.consultor = ticket.getConsultor();
        this.dataDeCriacao = ticket.getDataDeCriacao();
        this.dataDeResolucao = ticket.getDataDeResolucao();
        this.linkOD = ticket.getLinkOD();
        this.linhaDeProduto = ticket.getLinhaDeProduto();
    }


}
