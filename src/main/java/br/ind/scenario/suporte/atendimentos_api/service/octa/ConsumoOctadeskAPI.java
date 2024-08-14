package br.ind.scenario.suporte.atendimentos_api.service.octa;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoOctadeskAPI {
    private final String BASE_TICKET_URL = "https://api.octadesk.services/tickets/";
    private final String API_TOKEN;


    public ConsumoOctadeskAPI(OctaAPIConnection connection){
        this.API_TOKEN = connection.getApiToken();
    }

    public String getTicket(Integer number){
        String url = BASE_TICKET_URL +String.valueOf(number);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + API_TOKEN)
                .GET()
                .build();
        HttpResponse<String> response = null;
        System.out.println("Requesting...");
        System.out.println("Ticket Number " + number);
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Request finished: status ("+response.statusCode()+")");
        return response.body();
    }
}
