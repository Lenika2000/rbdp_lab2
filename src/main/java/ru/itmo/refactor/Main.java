package ru.itmo.refactor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Context context = new Context();
        Server server = new Server(context);
        server.start(18080);
        server.stop();
    }
}
