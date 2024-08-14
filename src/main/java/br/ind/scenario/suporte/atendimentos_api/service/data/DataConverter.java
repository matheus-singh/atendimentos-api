package br.ind.scenario.suporte.atendimentos_api.service.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter{
    private final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public <T> T stringToJson(String json, Class<T> $class) {
        try {
            return MAPPER.readValue(json, $class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
