package ru.itmo.refactor.command;

import ru.itmo.refactor.exceptions.IncorrectDataException;
import ru.itmo.refactor.model.Book;
import ru.itmo.refactor.utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    public void execute() throws IOException, IncorrectDataException, DateTimeParseException {
        BufferedReader br = new BufferedReader(reader);
        writer.write("Input author's name:\n");
        writer.flush();
        String authorName = br.readLine();
        Validator.validateLatinLetters(authorName, "The author of the book can only consist of Latin letters\n");
        writer.write("Input the books's title:\n");
        writer.flush();
        String title = br.readLine();
        Validator.validateLatinLetters(title, "The title of the book can only consist of Latin letters\n");
        writer.write("Input books's genres:\n");
        writer.flush();
        String genres = br.readLine();
        Validator.validateLatinLetters(genres, "The genres of the book can only consist of Latin letters\n");
        writer.write("Input books's publication date(format: yyyy-mm-dd):\n");
        writer.flush();
        LocalDate date = LocalDate.parse(br.readLine());
        writer.write("Input book's annotation:\n");
        writer.flush();
        String annotation = br.readLine();
        writer.write("Input books's isbn:\n");
        writer.flush();
        String isbn = br.readLine();
        Validator.validateDigits(isbn, "The ISBN of the book can only consist of 10 digits without spaces or other signs\n");
        Book book = new Book(title,authorName,genres,date,annotation,isbn);
        books.add(book);
    }
}
