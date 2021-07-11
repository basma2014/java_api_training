package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            try {
                String request = streamToString(exchange.getRequestBody());
                JsonNode node = new ObjectMapper().readTree(request);
                String url = node.get("url").asText();
                String body = "{\"id\": \"2\", \"url\": \"" + url + "\", \"message\": \"May the best code win\"}";
                Response(body, exchange);
            } catch (Exception e) {
                BadRequest(exchange);
            }
        }else {
            NotFound(exchange);
        }
    }

    public void Response(String body, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(202, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    public void NotFound(HttpExchange exchange) throws IOException {
        String body = "Not Found";
        exchange.sendResponseHeaders(404, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    public void BadRequest(HttpExchange exchange) throws IOException {
        String body = "Bad Request";
        exchange.sendResponseHeaders(404, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }
    public static String streamToString(InputStream s) throws IOException
    {
        InputStreamReader input = new InputStreamReader(s, StandardCharsets.UTF_8);
        BufferedReader buffer = new BufferedReader(input);
        int tmp;
        StringBuilder buf = new StringBuilder();
        while ((tmp = buffer.read()) != -1)
        {
            buf.append((char) tmp);
        }
        buffer.close();
        input.close();
        return buf.toString();
    }
}

