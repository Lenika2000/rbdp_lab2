package ru.itmo.refactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private final Context context;
    private final ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private boolean isRunning = true;

    public Server(Context context) {
        this.context = context;
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server is running");
        while (isRunning) {
            serveClient(serverSocket.accept());
            System.out.println("Connection accepted");
        }
    }

    private void serveClient(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(clientSocket, context.getCompositions());
        clientHandlers.add(clientHandler);
        new Thread(clientHandler).start();
    }

    public void stop() throws IOException {
        isRunning = false;
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.stop();
        }
        serverSocket.close();
    }
}
