package br.ind.scenario.suporte.atendimentos_api.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomFieldData(@JsonAlias("linha_de_produto") String linhaDeProduto,
                              @JsonAlias("natureza") String natureza,
                              @JsonAlias("classificacao_duvida") String classificacao,
                              @JsonAlias("numero_de_serie") String numeroDeSerie,
                              @JsonAlias("mac_address") String macAdress,
                              @JsonAlias("versao_de_firmware_ac_e_modulos") String versaoFirmwareAcModulos,
                              @JsonAlias("em_garantia") String emGarantia,
                              @JsonAlias("instalacao") String instalacao,
                              @JsonAlias("etapa_do_processo_de_manutencao") String etapaDoProcessoDeManutencao,
                              @JsonAlias("nota_fiscal") Long notaFiscal,
                              @JsonAlias("causa_do_problema") String causaDoProblema,
                              @JsonAlias("categorias_solicitacao") String categoria,
                              @JsonAlias("tipo_de_agendamento") String tipoDeAgendamento,
                              @JsonAlias("time_responsavel_pela_anlise") String timeResponsavelPelaAnalise,
                              @JsonAlias("problema_de_produto") String problemaDeProduto,
                              @JsonAlias("relatorio") String relatorio) {
}
