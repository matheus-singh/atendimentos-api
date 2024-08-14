package br.ind.scenario.suporte.atendimentos_api.service.data;

public interface IDataConverter {
    <T> T stringToJson(String json, Class<T> $class );
}
