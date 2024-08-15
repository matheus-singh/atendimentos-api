package br.ind.scenario.suporte.atendimentos_api.service.octa;

import br.ind.scenario.suporte.atendimentos_api.util.DateUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsumoOctadeskAPI {
    private final String BASE_TICKET_URL = "https://api.octadesk.services/tickets/";
    private final String API_TOKEN;


    public ConsumoOctadeskAPI(OctaAPIConnection connection){
        this.API_TOKEN = connection.getApiToken();
    }

    private HttpResponse<String> octaHttpRequest(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + API_TOKEN)
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Request finished: status ("+response.statusCode()+")");
        return response;
    }

    public String getTicket(Integer number){
        String url = BASE_TICKET_URL +String.valueOf(number);
        System.out.println("Requesting ticket number "+number+"...");
        HttpResponse<String> response = octaHttpRequest(url);
        return response.body();
    }

    public String getTicketsOfTheWeek() {
        String dateString = DateUtils.getLastWeekDateFromTodayAsString();
        String encodedDate = URLEncoder.encode(">" + dateString, StandardCharsets.UTF_8);
        String url = BASE_TICKET_URL + "search?openDate=" + encodedDate + "&take=100&page=";

        HttpResponse<String> response = octaHttpRequest(url);
        HttpHeaders headers = response.headers();
        if (headers.firstValue("total-pages").isPresent()){
            Integer totalPages = Integer.valueOf(headers.firstValue("total-pages").get());
            List<String> allTickets = new ArrayList<>();
            for (int i=1; i<=totalPages; i++){
                System.out.println("Requesting tickets of page "+i+"/"+totalPages);
                HttpResponse<String> responseForPage = octaHttpRequest(url+i);
                // Remove os colchetes [ ] do início e fim de cada resposta JSON
                String trimmedResponse = responseForPage.body().substring(1, responseForPage.body().length() - 1);
                allTickets.add(trimmedResponse);
            }
            return "[" + String.join(",", allTickets) + "]";
        }
        return null;
    }


}
