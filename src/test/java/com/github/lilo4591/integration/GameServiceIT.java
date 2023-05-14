package com.github.lilo4591.integration;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameServiceIT {

    private static final Logger LOGGER = Logger.getLogger(GameServiceIT.class.getName());
    private static final String TARGET_URL = "http://localhost:8080/game";

    private static URI targetURI;

    @BeforeAll
    public void init() throws URISyntaxException {
        targetURI = new URI(TARGET_URL);
    }

    @Test
    public void testGetHappyFlow() throws IOException, InterruptedException {
        String expectedResult = "{\"rows\":20,\"columns\":40}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(targetURI)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        LOGGER.info(String.format("Response: %s", response.body()));
        assertEquals(expectedResult, response.body());
    }

    @Test
    public void testPostHappyFlow() throws IOException, InterruptedException {
        String requestBody = "{\"coordinates\":[[2,0],[0,0],[0,1],[0,1]]}";
        String expectedResult = "{\"coordinates\":[[1,0],[1,1]]}";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(targetURI)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        LOGGER.info(String.format("Response: %s", response.body()));
        assertEquals(expectedResult, response.body());
    }

}
