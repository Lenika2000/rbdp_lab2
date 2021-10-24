package ru.itmo.refactor;

import ru.itmo.refactor.model.Book;
import java.util.concurrent.CopyOnWriteArrayList;

public class Context {
    private final CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();

    public Context() {
    }

    public CopyOnWriteArrayList<Book> getCompositions() {
        return books;
    }
}
