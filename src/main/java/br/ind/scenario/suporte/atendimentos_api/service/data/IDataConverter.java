package br.ind.scenario.suporte.atendimentos_api.service.data;

import java.util.List;

public interface IDataConverter {
    <T> T stringToJsonObject(String json, Class<T> $class );
    <T> List<T> stringToJsonObjectList(String json, Class<T> $class);
}
