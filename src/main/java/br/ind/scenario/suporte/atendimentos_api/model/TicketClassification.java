package br.ind.scenario.suporte.atendimentos_api.model;

public enum TicketClassification {
    ASSISTENCIA_TECNICA ("Assistência técnica"),
    DUVIDA ("Dúvida"),
    PROBLEMA_DESENVOLVIMENTO ("Problemas para o Desenvolvimento"),
    PROBLEMA ("Problema"),
    SOLICITACAO ("Solicitação"),
    TROCA ("Troca"),
    SEM_CLASSIFICACAO(null);

    private String classificacaoEmPortugues;

    TicketClassification(String classificacaoEmPortugues){
        this.classificacaoEmPortugues = classificacaoEmPortugues;
    }

    public static TicketClassification fromString(String text) {
        if (text == null){
            return TicketClassification.SEM_CLASSIFICACAO;
        }
        for (TicketClassification classificacao : TicketClassification.values()) {
            if (classificacao.classificacaoEmPortugues == null){
                if(text == null){
                    return classificacao;
                }
            } else {
                if (classificacao.classificacaoEmPortugues.equalsIgnoreCase(text)) {
                    return classificacao;
                }
            }
        }
        throw new IllegalArgumentException("Nenhuma classificacao encontrada para a string fornecida: " + text);
    }
}