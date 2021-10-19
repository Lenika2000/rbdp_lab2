package ru.itmo.refactor.command;

import ru.itmo.refactor.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.util.Collection;

public class AddCommand implements Command {

    private final Reader reader;
    private final Writer writer;
    private final Collection<Book> books;

    public AddCommand(Reader reader, Writer writer, Collection<Book> books) {
        this.reader = reader;
        this.writer = writer;
        this.books = books;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader br = new BufferedReader(reader);
        writer.write("Input author's name:\n");
        writer.flush();
        String authorName = br.readLine();
        writer.write("Input the books's name:\n");
        writer.flush();
        String name = br.readLine();
        writer.write("Input books's genres:\n");
        writer.flush();
        String genres = br.readLine();
        writer.write("Input books's publication date(format: yyyy-mm-dd):\n");
        writer.flush();
        LocalDate date = LocalDate.parse(br.readLine());
        writer.write("Input book's annotation:\n");
        writer.flush();
        String annotation = br.readLine();
        writer.write("Input books's isbn:\n");
        writer.flush();
        String isbn = br.readLine();
        Book book = new Book(name,authorName,genres,date,annotation,isbn);
        books.add(book);
    }
}
