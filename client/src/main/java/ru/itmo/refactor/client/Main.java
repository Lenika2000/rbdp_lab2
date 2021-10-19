package ru.itmo.refactor.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client client = null;
        try {
            client = new Client("127.0.0.1", 18080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (client.isRunning) {
            String str = null;
            try {
                str = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
            client.sendMessage(str);
        }
        try {
            client.stopConnection();
        } catch (IOException e) {
            // todo ошибка отключения сервера
            e.printStackTrace();
        }
    }
}
