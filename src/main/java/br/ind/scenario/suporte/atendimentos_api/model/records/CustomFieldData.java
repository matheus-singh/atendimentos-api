package br.ind.scenario.suporte.atendimentos_api.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomFieldData(@JsonAlias("linha_de_produto") String linhaDeProduto,
                              @JsonAlias("natureza") String natureza,
                              @JsonAlias("relatorio") String relatorio) {
}
