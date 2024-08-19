package br.ind.scenario.suporte.atendimentos_api.model;

import java.util.Optional;

public enum LinhaDeProduto {
    CLASSIC("Classic"),
    EMBRACE("Embrace"),
    COMPACT_WALL("Compact Wall"),
    NAO_SE_APLICA("Não se aplica"),
    SEM_LINHA_DE_PRODUTO(null);

    private final String linhaDeProdutoEmPortugues;

    LinhaDeProduto(String linhaDeProdutoEmPortugues) {
        this.linhaDeProdutoEmPortugues = linhaDeProdutoEmPortugues;
    }

    public static LinhaDeProduto fromString(String text) {
        return findByPortugueseName(text).orElse(LinhaDeProduto.SEM_LINHA_DE_PRODUTO);
    }

    private static Optional<LinhaDeProduto> findByPortugueseName(String text) {
        for (LinhaDeProduto linhaDeProduto : LinhaDeProduto.values()) {
            if (linhaDeProduto.linhaDeProdutoEmPortugues != null &&
                    linhaDeProduto.linhaDeProdutoEmPortugues.equalsIgnoreCase(text)) {
                return Optional.of(linhaDeProduto);
            }
        }
        return Optional.empty();
    }
}
