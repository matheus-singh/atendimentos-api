package br.ind.scenario.suporte.atendimentos_api.model;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.util.DateUtils;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

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
    private Topic topico;

    @Enumerated(EnumType.STRING)
    private TopicClassification classificacaoDoTopico;

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
    private String numeroDeSerie;

    @Enumerated(EnumType.STRING)
    private LinhaDeProduto linhaDeProduto;

    public Ticket(){}

    public Ticket(TicketSearchData ticketSearchData){
        this.octaId = ticketSearchData.octaId();
        this.numero = ticketSearchData.numero();
        this.status = TicketStatus.fromString(ticketSearchData.status());
        this.topico = Topic.fromString(ticketSearchData.topicName());
        this.classificacaoDoTopico = TopicClassification.fromString(ticketSearchData.topicGroupName());
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
            this.numeroDeSerie = "-";
        } else {
            this.linhaDeProduto = LinhaDeProduto.fromString(ticketSearchData.customFieldData().linhaDeProduto());
            this.numeroDeSerie = ticketSearchData.customFieldData().numeroSerie();
        }

        Optional<Integer> interactionsConteiner = Optional.ofNullable(ticketSearchData.interactions());

        if(interactionsConteiner.isPresent() && interactionsConteiner.get()>1){
            Optional<String> topicoUpdate = Optional.ofNullable(ticketSearchData.lastInteraction().propertyChanges().topicName());
            Optional<String> classificacaoTopicoUpdate = Optional.ofNullable(ticketSearchData.lastInteraction().propertyChanges().topicGroupName());
            topicoUpdate.ifPresent(t -> this.topico = Topic.fromString(t));
            classificacaoTopicoUpdate.ifPresent(c -> this.classificacaoDoTopico = TopicClassification.fromString(c));
        }
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
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
        return "Ticket{\n" +
                "id=" + id + "\n" +
                "octaId='" + octaId + '\'' + "\n" +
                "numero=" + numero + "\n" +
                "status=" + status + "\n" +
                "topico=" + topico + "\n" +
                "classificacaoDoTopico=" + classificacaoDoTopico + "\n" +
                "titulo='" + titulo + '\'' + "\n" +
                "revenda='" + revenda + '\'' + "\n" +
                "tecnico='" + tecnico + '\'' + "\n" +
                "emailDoTecnico='" + emailDoTecnico + '\'' + "\n" +
                "consultor='" + consultor + '\'' + "\n" +
                "emailConsultor='" + emailConsultor + '\'' + "\n" +
                "dataDeCriacao=" + dataDeCriacao + "\n" +
                "dataDeResolucao=" + dataDeResolucao + "\n" +
                "linkOD='" + linkOD + '\'' + "\n" +
                "produto='" + produto + '\'' + "\n" +
                "linhaDeProduto=" + linhaDeProduto + "\n" +
                '}';
    }


    public Topic getTopico() {
        return topico;
    }

    public void setTopico(Topic topico) {
        this.topico = topico;
    }

    public TopicClassification getClassificacaoDoTopico() {
        return classificacaoDoTopico;
    }

    public void setClassificacaoDoTopico(TopicClassification classificacaoDoTopico) {
        this.classificacaoDoTopico = classificacaoDoTopico;
    }
}
