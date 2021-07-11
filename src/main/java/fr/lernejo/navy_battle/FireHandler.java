package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.net.URI;
import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class FireHandler implements HttpHandler {

    final protected Game game;

    public FireHandler(Game game){
        this.game=game;
    }
    public String constructResponseBody(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String cell = query.substring(query.indexOf("cell=")+5).trim();
        int x = Integer.parseInt(cell.replace(Character.toString(cell.charAt(0)), "")) - 1;
        int y = cell.charAt(0) - 65;
        String shipState; Boolean shipLeft;
        shipState = getConsequence(x, y);
        shipLeft = game.isShipLeftOnGrid();
        return "{\"consequence\": \"" + shipState + "\", \"shipLeft\": " + shipLeft + "}";
    }
    public void randomFire(int myPort, int adversaryPort) {
        Random random = new Random();
        char randomLetter = (char)(random.nextInt(10) + 65); int randomY = random.nextInt(10) + 1;
        String coordinates = randomLetter + Integer.toString(randomY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + adversaryPort + "/api/game/fire?cell=" + coordinates))
            .setHeader("Accept", "application/json").setHeader("Content-Type", "application/json")
            .setHeader("X-Adversary-Port", Integer.toString(myPort))
            .setHeader("X-My-Port", Integer.toString(adversaryPort)).GET().build();
        client.sendAsync(getRequest, HttpResponse.BodyHandlers.ofString());
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String body; int adversaryPort = 8000; int myPort = 9000;
        if (exchange.getRequestMethod().equals("GET")) {
            try {
                if (exchange.getRequestHeaders().getFirst("X-Adversary-Port") != null) {
                    adversaryPort = Integer.parseInt(exchange.getRequestHeaders().getFirst("X-Adversary-Port"));
                }
                if (exchange.getRequestHeaders().getFirst("X-My-Port") != null) {
                    myPort = Integer.parseInt(exchange.getRequestHeaders().getFirst("X-My-Port"));
                }
                //String cell = extract_cell(exchange.getRequestURI().getQuery());
                //String consequence = this.game.takeFireFromEnemy(cell.charAt(0) - 'A', Integer.parseInt(cell.substring(1)) - 1);
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                body = constructResponseBody(exchange);
                Response(body,exchange);

            }catch(Exception e){
                BadRequest(exchange);
            }
        }
        else
            NotFound(exchange);
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

    public String getConsequence(int x, int y) {
        Ship ship = game.get_grid()[x][y];
        if (ship != null) {
            game.hitShip(x, y);
            if (ship.isOk(game)) {
                return "hit";
            } else {
                return "sunk";
            }
        }
        return "miss";
    }

}

