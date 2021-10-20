package ru.itmo.refactor.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.Writer;

public class HelpCommand implements Command {

    private final Writer writer;

    public HelpCommand(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void execute() throws IOException {
        writer.write("Commands:\n");
        writer.write("* find - find book in catalog\n");
        writer.write("* add - add a new book\n");
        writer.write("* help - get information about commands\n");
        writer.write("* quit - disconnect from the server and exit the program\n");
        writer.flush();
    }
}
