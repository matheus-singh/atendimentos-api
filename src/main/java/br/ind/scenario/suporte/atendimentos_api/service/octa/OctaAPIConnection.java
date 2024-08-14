package br.ind.scenario.suporte.atendimentos_api.service.octa;

import br.ind.scenario.suporte.atendimentos_api.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

public class OctaAPIConnection {
    public String getApiToken() {
        ObjectMapper objectMapper = new ObjectMapper();

        String subdomain = "scenarioautomation";
        String url = "https://api.octadesk.services/login";  // Replace with your actual URL
        String payload = "{"
                + "\"username\": \""+ System.getenv("OCTA_USER")+"\","
                + "\"password\": \""+ System.getenv("OCTA_PASSWORD")+"\""
                + "}";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("subDomain", subdomain)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> responseMap = JsonUtils.parseJsonIntoMap(response.body());

        System.out.println("Octa Connection: status ("+response.statusCode()+")");
        return responseMap.get("token");
    }
}
