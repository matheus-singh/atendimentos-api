package br.ind.scenario.suporte.atendimentos_api.model;

import java.util.Optional;

public enum TopicClassification {
    SEM_CLASSIFICACAO(null),
    CLOUD("1 - Cloud"),
    CONFIG("1 - Config"),
    E_APP("1 - E-App"),
    INSTALACAO("1 - Instalação"),
    INTEGRACAO("1 - Integração"),
    RECURSOS("1 - Recursos"),
    DRIVER("2 - Driver"),
    INFRA_INSTALACAO ("2 - Infra / Instalação"),
    PROGRAMACAO ("2 - Programação"),
    BUG ("3 - BUG"),
    OUTROS ("3 - Outros"),
    CONTATO_NF_COMERCIAL ("4 - Contato / NF / Comercial"),
    DESENVOLVIMENTO_DE_DRIVER ("4 - Driver"),
    FEEDBACK_ASSISTENCIA ("4 - Feedback Assistência"),
    FEEDBACK_TICKET ("4 - Feedback Ticket"),
    OUTRAS ("4 - Outros"),
    TREINAMENTO ("4 - Treinamento"),
    EM_GARANTIA ("5 - Em Garantia"),
    FORA_DE_GARANTIA ("5 - Fora de Garantia"),
    ENVIO_PARA_ASSISTENCIA ("6 - Assistência Técnica"),
    CONFIG_E2 ("7 - Config"),
    EMBRACE_APP ("7 - E-App"),
    PRODUTO ("7 - Produto"),
    OUTROS_RECURSOS ("7 - Recursos");

    private final String classificacaoEmPortugues;

    TopicClassification(String classificacaoEmPortugues) {
        this.classificacaoEmPortugues = classificacaoEmPortugues;
    }

    public static TopicClassification fromString(String text) {
        return findByPortugueseName(text).orElse(TopicClassification.SEM_CLASSIFICACAO);
    }

    private static Optional<TopicClassification> findByPortugueseName(String text) {
        for (TopicClassification classificacao : TopicClassification.values()) {
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
