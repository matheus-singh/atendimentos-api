package br.ind.scenario.suporte.atendimentos_api.service.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

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

    public <T> List<T> stringToJsonList(String json, Class<T> $class) {
        try {
            JavaType type = MAPPER.getTypeFactory().constructCollectionType(List.class, $class);
            return MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
