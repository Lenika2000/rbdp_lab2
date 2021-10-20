package ru.itmo.refactor;

import ru.itmo.refactor.model.Book;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Reader reader;
    private final Writer writer;
    private final Collection<Book> books;

    public ClientHandler(Socket socket, Collection<Book> books) throws IOException {
        this.socket = socket;
        this.books = books;
        writer = new OutputStreamWriter(socket.getOutputStream());
        reader = new InputStreamReader(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            CommandHandler commandHandler = new CommandHandler(reader, writer, books, true);
            commandHandler.handleCommands();
            stop();
        } catch (IOException e) {
            System.out.print("Client disconnected");
        }
    }

    public void stop() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
