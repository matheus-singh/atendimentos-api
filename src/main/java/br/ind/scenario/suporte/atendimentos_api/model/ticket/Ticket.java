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

    @Column(unique = true)
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
}
