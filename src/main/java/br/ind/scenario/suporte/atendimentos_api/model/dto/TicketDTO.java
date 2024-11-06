package br.ind.scenario.suporte.atendimentos_api.model.dto;

import br.ind.scenario.suporte.atendimentos_api.model.LinhaDeProduto;
import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.TicketStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TicketDTO {

    private Long number;
    private TicketStatus status;
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
    private LinhaDeProduto linhaDeProduto;
    private String numeroDeSerie;
    private Boolean isABug;

    public TicketDTO(){
    }

    public TicketDTO(Ticket ticket){
        this.number = ticket.getNumero();
        this.status = ticket.getStatus();
        this.titulo = ticket.getTitulo();
        this.revenda = ticket.getRevenda();
        this.tecnico = ticket.getTecnico();
        this.emailDoTecnico = ticket.getEmailDoTecnico();
        this.consultor = ticket.getConsultor();
        this.emailConsultor = ticket.getEmailConsultor();
        this.dataDeCriacao = ticket.getDataDeCriacao();
        this.dataDeResolucao = ticket.getDataDeResolucao();
        this.linkOD = ticket.getLinkOD();
        this.produto = ticket.getProduto();
        this.linhaDeProduto = ticket.getLinhaDeProduto();
        this.numeroDeSerie = ticket.getNumeroDeSerie();
        this.isABug = ticket.getIsABug();
    }

    public Boolean getIsABug() {
        return isABug;
    }

    public void setIsABug(Boolean ABug) {
        isABug = ABug;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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
}
