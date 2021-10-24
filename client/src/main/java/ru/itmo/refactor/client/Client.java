package ru.itmo.refactor.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;
    public boolean isRunning = true;

    public Client(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // печать пользовательской команды в консоль
        new Thread(() -> {
            try {
                while (isRunning) {
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                System.out.println("Connection reset. Check server status!");
            }
        }).start();
    }

    public void sendMessage(String msg) {
        // отправка пользовательской команды на сервер
        out.println(msg);
        if (msg.equals("quit")) {
            isRunning = false;
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
