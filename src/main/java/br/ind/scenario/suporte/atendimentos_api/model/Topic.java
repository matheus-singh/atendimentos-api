package br.ind.scenario.suporte.atendimentos_api.model;

import java.util.Optional;

public enum Topic {
    ASSISTENCIA_TECNICA("Assistência técnica"),
    DUVIDA("Dúvida"),
    EM_ANALISE("Análise", "Análise DEV"),
    PROBLEMA_REVENDA("Problema Revenda", "Problema"),
    SOLICITACAO("Solicitação"),
    TROCA("Troca"),
    PROBLEMA_SCENARIO("Problema Scenario", "7 - Problema Scenario"),
    TOPICO_VAZIO((String) null);

    private final String[] classificacoesEmPortugues;

    Topic(String... classificacoesEmPortugues) {
        this.classificacoesEmPortugues = classificacoesEmPortugues;
    }

    public static Topic fromString(String text) {
        return findByPortugueseName(text).orElse(Topic.TOPICO_VAZIO);
    }

    private static Optional<Topic> findByPortugueseName(String text) {
        for (Topic topico : Topic.values()) {
            if (topico.classificacoesEmPortugues != null) {
                for (String classificacao : topico.classificacoesEmPortugues) {
                    if (classificacao != null && classificacao.equalsIgnoreCase(text)) {
                        return Optional.of(topico);
                    }
                }
            } else if (text == null) {
                return Optional.of(topico);
            }
        }
        return Optional.empty();
    }
}
