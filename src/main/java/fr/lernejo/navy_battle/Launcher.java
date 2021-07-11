package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) throws IOException {
            int port = Integer.parseInt(args[0]);
            if(args.length == 1) {
                System.out.println("Tentative de connexion sur le port: " + port);
                new Server().startServer(port);
            }if (args.length == 2) {
                int portOther = Integer.parseInt(args[1]);
                System.out.println("Il y a 2 ports, le second est le port: " + portOther);
            }
        }

    public void startServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.createContext("/ping", new PingHandler());
        server.createContext("/api/game/start", new StartHandler());
        //server.createContext("/api/game/fire", new FireHandler(game));
        server.setExecutor(executor);
        server.start();
    }
}

