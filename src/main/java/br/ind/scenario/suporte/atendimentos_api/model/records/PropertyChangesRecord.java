package br.ind.scenario.suporte.atendimentos_api.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PropertyChangesRecord (
        @JsonAlias("topicName") String topicName,
        @JsonAlias("topicGroupName") String topicGroupName
) {

}
