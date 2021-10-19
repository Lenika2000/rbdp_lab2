package ru.itmo.refactor.command;

import java.io.IOException;
import java.io.Writer;

public class QuitCommand implements Command {

    private Writer writer;
    private Runnable performExit;

    public QuitCommand(Writer writer, Runnable performExit) {
        this.writer = writer;
        this.performExit = performExit;
    }

    @Override
    public void execute() throws IOException {
        writer.write("Exiting the program\n");
        performExit.run();
    }
}
