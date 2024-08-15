package br.ind.scenario.suporte.atendimentos_api.model;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.util.DateUtils;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String octaId;

    private Long numero;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    private TicketClassification classificacao;

    private String titulo;
    private String revenda;
    private String tecnico;
    private String emailDoTecnico;
    private String consultor;
    private String emailConsultor;
    private LocalDate dataDeCriacao;
    private LocalDate dataDeResolucao;
    private String linkOD;
    private String produto;

    @Enumerated(EnumType.STRING)
    private LinhaDeProduto linhaDeProduto;

    public Ticket(){}

    public Ticket(TicketSearchData ticketSearchData){
        this.octaId = ticketSearchData.octaId();
        this.numero = ticketSearchData.numero();
        this.status = TicketStatus.fromString(ticketSearchData.status());
        this.classificacao = TicketClassification.fromString(ticketSearchData.classificacao());
        this.titulo = ticketSearchData.titulo();
        this.revenda = ticketSearchData.revenda();
        this.tecnico = ticketSearchData.tecnico();
        this.emailDoTecnico = ticketSearchData.emailDoTecnico();
        this.consultor = ticketSearchData.consultor();
        this.emailConsultor = ticketSearchData.emailConsultor();
        this.dataDeCriacao = DateUtils.createLocalDateFromString(ticketSearchData.dataDeCriacao());
        this.dataDeResolucao = DateUtils.createLocalDateFromString(ticketSearchData.dataDeResolucao());
        this.linkOD = ticketSearchData.linkOD();
        this.produto = ticketSearchData.produto();

        if(ticketSearchData.customFieldData() == null){
            this.linhaDeProduto = LinhaDeProduto.SEM_LINHA_DE_PRODUTO;
        } else {
            this.linhaDeProduto = LinhaDeProduto.fromString(ticketSearchData.customFieldData().linhaDeProduto());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOctaId() {
        return octaId;
    }

    public void setOctaId(String octaId) {
        this.octaId = octaId;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public TicketClassification getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(TicketClassification classificacao) {
        this.classificacao = classificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRevenda() {
        return revenda;
    }

    public void setRevenda(String revenda) {
        this.revenda = revenda;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getEmailDoTecnico() {
        return emailDoTecnico;
    }

    public void setEmailDoTecnico(String emailDoTecnico) {
        this.emailDoTecnico = emailDoTecnico;
    }

    public String getConsultor() {
        return consultor;
    }

    public void setConsultor(String consultor) {
        this.consultor = consultor;
    }

    public String getEmailConsultor() {
        return emailConsultor;
    }

    public void setEmailConsultor(String emailConsultor) {
        this.emailConsultor = emailConsultor;
    }

    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDate dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public LocalDate getDataDeResolucao() {
        return dataDeResolucao;
    }

    public void setDataDeResolucao(LocalDate dataDeResolucao) {
        this.dataDeResolucao = dataDeResolucao;
    }

    public String getLinkOD() {
        return linkOD;
    }

    public void setLinkOD(String linkOD) {
        this.linkOD = linkOD;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public LinhaDeProduto getLinhaDeProduto() {
        return linhaDeProduto;
    }

    public void setLinhaDeProduto(LinhaDeProduto linhaDeProduto) {
        this.linhaDeProduto = linhaDeProduto;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", octaId='" + octaId + '\'' +
                ", numero=" + numero +
                ", status=" + status +
                ", classificacao=" + classificacao +
                ", titulo='" + titulo + '\'' +
                ", revenda='" + revenda + '\'' +
                ", tecnico='" + tecnico + '\'' +
                ", emailDoTecnico='" + emailDoTecnico + '\'' +
                ", consultor='" + consultor + '\'' +
                ", emailConsultor='" + emailConsultor + '\'' +
                ", dataDeCriacao=" + (dataDeCriacao != null ? dataDeCriacao : "N/A") +
                ", dataDeResolucao=" + (dataDeResolucao != null ? dataDeResolucao : "N/A") +
                ", linkOD='" + linkOD + '\'' +
                ", produto='" + produto + '\'' +
                ", linhaDeProduto=" + linhaDeProduto +
                '}';
    }

}
