package br.ind.scenario.suporte.atendimentos_api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    public static void prettyPrintJson(String json){
        try {
            JsonNode jsonNode = mapper.readTree(json);
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            System.out.println(prettyJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> parseJsonIntoMap(String json) {
        try {
            return mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
