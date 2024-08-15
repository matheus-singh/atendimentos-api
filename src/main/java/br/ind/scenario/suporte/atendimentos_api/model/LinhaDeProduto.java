package br.ind.scenario.suporte.atendimentos_api.model;

public enum LinhaDeProduto {
    CLASSIC("Classic"),
    EMBRACE("Embrace"),
    COMPACT_WALL("Compact Wall"),
    NAO_SE_APLICA("Não se aplica"),
    SEM_LINHA_DE_PRODUTO(null);


    private String linhaDeProdutoEmPortugues;

    LinhaDeProduto(String linhaDeProdutoEmPortugues){
        this.linhaDeProdutoEmPortugues = linhaDeProdutoEmPortugues;
    }

    public static LinhaDeProduto fromString(String text){
        if (text == null){
            return LinhaDeProduto.SEM_LINHA_DE_PRODUTO;
        }
        for (LinhaDeProduto linhaDeProduto : LinhaDeProduto.values()){
            if (linhaDeProduto.linhaDeProdutoEmPortugues.equalsIgnoreCase(text)){
                return linhaDeProduto;
            }
        }
        throw new IllegalArgumentException("Nenhuma linha de produto encontrada para a string fornecida: " + text);
    }
}
