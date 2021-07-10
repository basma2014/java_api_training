package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public void createServer(int port) throws IOException {
        //int port = Integer.parseInt(System.getenv().getOrDefault("PORT", args[0]));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ping", (var exchange) -> {
            String body = "Are you OK ?";
            exchange.sendResponseHeaders(200, body.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(body.getBytes());
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);
        server.start();
        server.stop(20);
    }

    public void stopServer() {
        //server.stop();
    }
}
