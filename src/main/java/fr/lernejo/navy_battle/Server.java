package fr.lernejo.navy_battle;


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public void startServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.createContext("/ping", new PingHandler());
        server.createContext("/api/game/start", new StartHandler());
        //server.createContext("/api/game/fire", new FireHandler());
        server.setExecutor(executor);
        server.start();
    }

}





