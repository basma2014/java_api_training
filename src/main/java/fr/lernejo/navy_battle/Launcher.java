package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            try {
                int port = Integer.parseInt(args[0]);
                System.out.println("Creating server listening on port " + port);
                new Server().createServer(port);
            } catch (NumberFormatException e) {
                System.err.println("Port number " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        else{
            System.err.println("Port number should have been entered.");
            System.exit(-1);
        }
    }
}
