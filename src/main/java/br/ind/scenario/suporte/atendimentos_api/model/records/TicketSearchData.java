package br.ind.scenario.suporte.atendimentos_api.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TicketSearchData(
        @JsonAlias("id") String octaId,
        @JsonAlias("number") Long numero,
        @JsonAlias("currentStatusName") String status,
        @JsonAlias("summary") String titulo,
        @JsonAlias("organizationName") String revenda,
        @JsonAlias("requesterName") String tecnico,
        @JsonAlias("assignedName") String consultor,
        @JsonAlias("openDate") String dataDeCriacao,
        @JsonAlias("doneDate") String dataDeResolucao,
        @JsonAlias("octadeskNumberUrl") String linkOD,
        @JsonAlias("productMembersJoin") String produtosOuServicos,
        @JsonAlias("customField") CustomFieldData customFieldData
        ) {
}
