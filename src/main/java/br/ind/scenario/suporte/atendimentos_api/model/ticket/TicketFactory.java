package br.ind.scenario.suporte.atendimentos_api.model.ticket;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.model.ticket.relatorios.*;
import org.springframework.stereotype.Component;

@Component
public class TicketFactory {

    public Ticket create(TicketSearchData ticketSearchData){
        String relatorio = ticketSearchData.customFieldData().relatorio();
        return switch (relatorio) {
            case "Sugestões" -> new TicketSugestoes(ticketSearchData);
            case "Dúvida" -> new TicketDuvida(ticketSearchData);
            case "Assistência técnica" -> new TicketAssistenciaTecnica(ticketSearchData);
            case "Troca" -> new TicketTroca(ticketSearchData);
            case "Ordem de manutenção" -> new TicketOrdemDeManutencao(ticketSearchData);
            case "Problema revenda" -> new TicketProblemaRevenda(ticketSearchData);
            case "Solicitação" -> new TicketSolicitacao(ticketSearchData);
            case "Agendamento" -> new TicketAgendamento(ticketSearchData);
            case "Em análise" -> new TicketEmAnalise(ticketSearchData);
            default -> null;
        };
    }

    public Ticket updateTicket(Ticket existingTicket, Ticket ticket) {
        String relatorio = ticket.getRelatorio();
        switch(relatorio) {
            case "sugestao":
                TicketSugestoes newTicketSugestoes = (TicketSugestoes) ticket;
                TicketSugestoes ticketToUpdateSugestoes = (TicketSugestoes) existingTicket;
                ticketToUpdateSugestoes.update(newTicketSugestoes);
                return ticketToUpdateSugestoes;
            case "duvida":
                TicketDuvida newTicketDuvida = (TicketDuvida) ticket;
                TicketDuvida ticketToUpdateDuvida = (TicketDuvida) existingTicket;
                ticketToUpdateDuvida.update(newTicketDuvida);
                return ticketToUpdateDuvida;
            case "assistencia tecnica":
                TicketAssistenciaTecnica newTicketAssistenciaTecnica = (TicketAssistenciaTecnica) ticket;
                TicketAssistenciaTecnica ticketToUpdateAssistenciaTecnica = (TicketAssistenciaTecnica) existingTicket;
                ticketToUpdateAssistenciaTecnica.update(newTicketAssistenciaTecnica);
                return ticketToUpdateAssistenciaTecnica;
            case "troca":
                TicketTroca newTicketTroca = (TicketTroca) ticket;
                TicketTroca ticketToUpdateTroca = (TicketTroca) existingTicket;
                ticketToUpdateTroca.update(newTicketTroca);
                return ticketToUpdateTroca;
            case "ordem de manutencao":
                TicketOrdemDeManutencao newTicketOrdemDeManutencao = (TicketOrdemDeManutencao) ticket;
                TicketOrdemDeManutencao ticketToUpdateOrdemDeManutencao = (TicketOrdemDeManutencao) existingTicket;
                ticketToUpdateOrdemDeManutencao.update(newTicketOrdemDeManutencao);
                return ticketToUpdateOrdemDeManutencao;
            case "problema revenda":
                TicketProblemaRevenda newTicketProblemaRevenda = (TicketProblemaRevenda) ticket;
                TicketProblemaRevenda ticketToUpdateProblemaRevenda = (TicketProblemaRevenda) existingTicket;
                ticketToUpdateProblemaRevenda.update(newTicketProblemaRevenda);
                return ticketToUpdateProblemaRevenda;
            case "solicitacao":
                TicketSolicitacao newTicketSolicitacao = (TicketSolicitacao) ticket;
                TicketSolicitacao ticketToUpdateSolicitacao = (TicketSolicitacao) existingTicket;
                ticketToUpdateSolicitacao.update(newTicketSolicitacao);
                return ticketToUpdateSolicitacao;
            case "agendamento":
                TicketAgendamento newTicketAgendamento = (TicketAgendamento) ticket;
                TicketAgendamento ticketToUpdateAgendamento = (TicketAgendamento) existingTicket;
                ticketToUpdateAgendamento.update(newTicketAgendamento);
                return ticketToUpdateAgendamento;
            case "em analise":
                TicketEmAnalise newTicketEmAnalise = (TicketEmAnalise) ticket;
                TicketEmAnalise ticketToUpdateEmAnalise = (TicketEmAnalise) existingTicket;
                ticketToUpdateEmAnalise.update(newTicketEmAnalise);
                return ticketToUpdateEmAnalise;
            default:
                return null;
        }
    }
}
