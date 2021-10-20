package ru.itmo.refactor.command;

import ru.itmo.refactor.exceptions.IncorrectDataException;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, IncorrectDataException;
}
