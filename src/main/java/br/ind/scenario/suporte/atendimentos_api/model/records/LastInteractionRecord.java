package br.ind.scenario.suporte.atendimentos_api.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LastInteractionRecord(
        @JsonAlias("propertiesChanges") PropertyChangesRecord propertyChanges
) {
}
