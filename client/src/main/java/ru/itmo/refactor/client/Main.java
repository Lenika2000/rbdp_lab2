package ru.itmo.refactor.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    public static void main(String[] args)  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client client = null;
        try {
            client = new Client("127.0.0.1", 18080);
        } catch (IOException e) {
            System.out.println("Connection refused. Check server status!");
        }
        while (client != null && client.isRunning) {
            String str = null;
            try {
                str = reader.readLine().trim();
            } catch (IOException e) {
                System.out.println("Connection is lost. Check server status!");
            }
            client.sendMessage(str);
        }
        try {
            if (client != null) client.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
