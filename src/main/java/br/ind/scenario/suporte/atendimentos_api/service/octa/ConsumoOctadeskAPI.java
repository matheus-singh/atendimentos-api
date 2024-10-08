package br.ind.scenario.suporte.atendimentos_api.service.octa;

import br.ind.scenario.suporte.atendimentos_api.util.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumoOctadeskAPI {
    private final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(ConsumoOctadeskAPI.class);

    private final String BASE_TICKET_URL = "https://api.octadesk.services/tickets/";

    // Cache do token e timestamp de expiração
    private String cachedToken = null;
    private Instant tokenExpiryTime = Instant.MIN; // Data muito antiga, forçando a primeira obtenção do token

    // Duração de 1 hora para o cache do token
    private static final Duration TOKEN_EXPIRY_DURATION = Duration.ofHours(1);

    /**
     * Método para obter o token de API, usando cache.
     * Se o token expirou ou não existe, uma nova requisição será feita.
     */
    private String getApiToken() {
        if (cachedToken == null || Instant.now().isAfter(tokenExpiryTime)) {
            logger.info("Token expirado ou não encontrado, solicitando novo token.");
            cachedToken = requestNewToken();
            tokenExpiryTime = Instant.now().plus(TOKEN_EXPIRY_DURATION); // Atualiza o timestamp de expiração
        } else {
            logger.info("Usando token em cache.");
        }
        return cachedToken;
    }

    /**
     * Realiza a requisição para obter um novo token.
     */
    private String requestNewToken() {
        String url = "https://api.octadesk.services/login";
        String subdomain = "scenarioautomation";

        String username = System.getenv("OCTA_USER");
        String password = System.getenv("OCTA_PASSWORD");

        HttpClient client = HttpClient.newHttpClient();

        String jsonPayload = "{"
                + "\"username\": \"" + username + "\","
                + "\"password\": \"" + password + "\""
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("subDomain", subdomain)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .timeout(Duration.ofSeconds(10))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            logger.info("Token Request: " + response.statusCode());
            JsonNode jsonResponse = MAPPER.readTree(responseBody);
            return jsonResponse.path("token").asText();
        } catch (Exception e) {
            logger.info("An Error has occurred in the token request: " + e.getMessage());
            return "";
        }
    }

    private HttpResponse<String> octaHttpRequest(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + this.getApiToken())
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Request finished: status ({})", response.statusCode());
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTicket(Integer number) {
        String url = BASE_TICKET_URL + String.valueOf(number);
        logger.info("Requesting ticket number: {}", number);
        HttpResponse<String> response = octaHttpRequest(url);
        return response.body();
    }

    public String getTicketsOfTheWeek() {
        String dateString = DateUtils.getLastWeekDateFromTodayAsString();
        String encodedDate = URLEncoder.encode(">" + dateString, StandardCharsets.UTF_8);
        String url = BASE_TICKET_URL + "search?openDate=" + encodedDate + "&take=100&page=";

        HttpResponse<String> response = octaHttpRequest(url);
        if (response.headers().firstValue("total-pages").isPresent()) {
            int totalPages = Integer.parseInt(response.headers().firstValue("total-pages").get());
            List<String> allTickets = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                logger.info("Requesting tickets... [page {}/{}]", i, totalPages);
                HttpResponse<String> responseForPage = octaHttpRequest(url + i);
                String trimmedResponse = responseForPage.body().substring(1, responseForPage.body().length() - 1);
                allTickets.add(trimmedResponse);
            }
            return "[" + String.join(",", allTickets) + "]";
        }
        return null;
    }

    public String getTicketsOfTheDay() {
        String dateString = DateUtils.getYesterdayDateAsString();
        String encodedDate = URLEncoder.encode(">" + dateString, StandardCharsets.UTF_8);
        String url = BASE_TICKET_URL + "search?openDate=" + encodedDate + "&take=100&page=";

        HttpResponse<String> response = octaHttpRequest(url);
        if (response.headers().firstValue("total-pages").isPresent()) {
            int totalPages = Integer.parseInt(response.headers().firstValue("total-pages").get());
            List<String> allTickets = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                logger.info("Requesting tickets... [page {}/{}]", i, totalPages);
                HttpResponse<String> responseForPage = octaHttpRequest(url + i);
                String trimmedResponse = responseForPage.body().substring(1, responseForPage.body().length() - 1);
                allTickets.add(trimmedResponse);
            }
            return "[" + String.join(",", allTickets) + "]";
        }
        return null;
    }
}
