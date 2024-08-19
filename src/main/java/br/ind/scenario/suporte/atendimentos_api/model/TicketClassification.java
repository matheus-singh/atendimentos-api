package br.ind.scenario.suporte.atendimentos_api.model;

import java.util.Optional;

public enum TicketClassification {
    ASSISTENCIA_TECNICA("Assistência técnica"),
    DUVIDA("Dúvida"),
    PROBLEMA_DESENVOLVIMENTO("Problemas para o Desenvolvimento"),
    PROBLEMA("Problema"),
    SOLICITACAO("Solicitação"),
    TROCA("Troca"),
    SEM_CLASSIFICACAO(null);

    private final String classificacaoEmPortugues;

    TicketClassification(String classificacaoEmPortugues) {
        this.classificacaoEmPortugues = classificacaoEmPortugues;
    }

    public static TicketClassification fromString(String text) {
        return findByPortugueseName(text).orElse(TicketClassification.SEM_CLASSIFICACAO);
    }

    private static Optional<TicketClassification> findByPortugueseName(String text) {
        for (TicketClassification classificacao : TicketClassification.values()) {
            if (classificacao.classificacaoEmPortugues == null && text == null) {
                return Optional.of(classificacao);
            } else if (classificacao.classificacaoEmPortugues != null &&
                    classificacao.classificacaoEmPortugues.equalsIgnoreCase(text)) {
                return Optional.of(classificacao);
            }
        }
        return Optional.empty();
    }
}
