package br.ind.scenario.suporte.atendimentos_api.service.data;

import java.util.List;

public interface IDataConverter {
    <T> T stringToJson(String json, Class<T> $class );
    <T> List<T> stringToJsonList(String json, Class<T> $class);
}
