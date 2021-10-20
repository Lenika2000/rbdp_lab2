package ru.itmo.refactor.utils;

import ru.itmo.refactor.exceptions.IncorrectDataException;

public class Validator {
    public static void validateLatinLetters(String field, String msg ) throws IncorrectDataException {
        if (!field.matches("[a-zA-Z ]*")) throw new IncorrectDataException(msg);
    }

    public static void validateDigits(String field, String msg ) throws IncorrectDataException {
        if (!field.matches("[0-9]*") || field.length() != 10) throw new IncorrectDataException(msg);
    }
}
