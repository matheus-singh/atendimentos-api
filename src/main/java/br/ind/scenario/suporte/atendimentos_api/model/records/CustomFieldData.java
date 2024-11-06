package br.ind.scenario.suporte.atendimentos_api.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomFieldData(@JsonAlias("linha_de_produto") String linhaDeProduto,
                              @JsonAlias("versao_do_config") String versaoConfig,
                              @JsonAlias("versao_do_eapp") String versaoEapp,
                              @JsonAlias("numero_de_serie") String numeroSerie,
                              @JsonAlias("bug") Boolean isABug,
                              @JsonAlias("versao_de_firmware_ac_e_modulos") String versaoFirmwareAC) {
}
