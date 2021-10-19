package ru.itmo.refactor.command;

import java.io.IOException;
import java.io.Writer;

public class UnknownCommand implements Command {

    private Writer writer;

    public UnknownCommand(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void execute() throws IOException {
        writer.write("Unknown command.\n");
    }
}
