package br.ind.scenario.suporte.atendimentos_api.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TicketSearchData(
        @JsonAlias("id") String octaId,
        @JsonAlias("number") Long numero,
        @JsonAlias("currentStatusName") String status,
        @JsonAlias("topicName") String classificacao,
        @JsonAlias("summary") String titulo,
        @JsonAlias("organizationName") String revenda,
        @JsonAlias("requesterName") String tecnico,
        @JsonAlias("requesterMail") String emailDoTecnico,
        @JsonAlias("assignedName") String consultor,
        @JsonAlias("assignedMail") String emailConsultor,
        @JsonAlias("openDate") String dataDeCriacao,
        @JsonAlias("doneDate") String dataDeResolucao,
        @JsonAlias("octadeskNumberUrl") String linkOD,
        @JsonAlias("productMembersJoin") String produto,
        @JsonAlias("customField") CustomFieldData customFieldData
        ) {
}
