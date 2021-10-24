package ru.itmo.refactor;

import ru.itmo.refactor.command.*;
import ru.itmo.refactor.exceptions.IncorrectDataException;
import ru.itmo.refactor.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.format.DateTimeParseException;
import java.util.Collection;

public class CommandHandler {
    private Reader reader;
    private Writer writer;
    private Collection<Book> books;
    private boolean isRunning;

    public CommandHandler(Reader reader, Writer writer, Collection<Book> books, boolean isRunning) {
        this.reader = reader;
        this.writer = writer;
        this.books = books;
        this.isRunning = isRunning;
    }

    public void handleCommands() throws IOException {
        BufferedReader reader = new BufferedReader(this.reader);
        new HelpCommand(writer).execute();
        while (isRunning) {
            writer.write("Input the command:\n");
            writer.flush();
            // чтение команды от клиента
            String cmd = reader.readLine().toLowerCase();
            Command command = getCmdFromStr(cmd);
            try {
                command.execute();
            } catch (IncorrectDataException e) {
                writer.write(e.getMessage() + "Command failed, please try again!\n");
            } catch (DateTimeParseException e) {
                writer.write("Books's publication date has format: yyyy-mm-dd. Command failed, please try again!\n");
            }
            writer.write("----\n");
            writer.flush();
        }
    }

    private Command getCmdFromStr(String cmd) {
        Command command;
        switch (cmd) {
            case "add":
                command = new AddCommand(reader, writer, books);
                break;
            case "find":
                command = new SearchCommand(reader, writer, books);
                break;
            case "quit":
                command = new QuitCommand(writer, () -> isRunning = false);
                break;
            case "help":
                command = new HelpCommand(writer);
                break;
            default:
                command = new UnknownCommand(writer);
                break;
        }
        return command;
    }
}
