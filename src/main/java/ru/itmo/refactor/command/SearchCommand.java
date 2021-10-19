package ru.itmo.refactor.command;
import ru.itmo.refactor.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class SearchCommand implements Command {

    private final Reader reader;
    private final Writer writer;
    private final Collection<Book> books;
    final int BY_NAME = 1;
    final int BY_AUTHOR = 2;
    final int BY_ISBN = 3;
    final int BY_KEYWORDS = 4;

    public SearchCommand(Reader reader, Writer writer, Collection<Book> books) {
        this.reader = reader;
        this.writer = writer;
        this.books = books;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader br = new BufferedReader(reader);
        Set<Book> selectedBooks = new HashSet<>();
        writer.write("Select search criteria:\n1.By name or its fragment\n" +
                "2.By the name of the author\n3.By ISBN\n4.By keywords\nPlease print number.\n");
        writer.flush();
        // todo только цифры(1,2,3,4)
        int flag = Integer.parseInt(br.readLine());
        switch (flag) {
            case BY_NAME:
                // todo только буквы
                writer.write("Please print book's name or its fragment\n");
                writer.flush();
                String name = br.readLine();
                selectedBooks = books.stream().filter(b -> b.getName().contains(name)).collect(Collectors.toSet());
                break;
            case BY_AUTHOR:
                // todo только буквы
                writer.write("Please print book's author\n");
                writer.flush();
                String author = br.readLine();
                selectedBooks = books.stream().filter(b -> b.getAuthorName().contains(author)).collect(Collectors.toSet());
                break;
            case BY_ISBN:
                //todo только число и определенное количество цифр
                writer.write("Please print book's ISBN\n");
                writer.flush();
                String isbn = br.readLine();
                selectedBooks = books.stream().filter(b -> b.getIsbn().contains(isbn)).collect(Collectors.toSet());
                break;
            case BY_KEYWORDS:
                writer.write("Please print keywords, use a space as a separator\n");
                writer.flush();
                String keywords = br.readLine();
                String[] values = keywords.split("\\s+");
                for(String val: values) {
                    selectedBooks.addAll(books.stream().filter(b -> b.getName().contains(val)).collect(Collectors.toSet()));
                    selectedBooks.addAll(books.stream().filter(b -> b.getAuthorName().contains(val)).collect(Collectors.toSet()));
                    selectedBooks.addAll(books.stream().filter(b -> b.getIsbn().contains(val)).collect(Collectors.toSet()));
                    selectedBooks.forEach(book -> book.setAnnotation(null));
                    // todo null поле у анотации при печати в консоль
                    selectedBooks.addAll(books.stream().filter(b -> b.getAnnotation() != null && b.getAnnotation().contains(val)).collect(Collectors.toSet()));
                }
                break;
            default:
                break;
        }
        if (books.size() == 0) {
            writer.write("No books were found by this criteria.\n");
        } else {
            selectedBooks.forEach(book -> {
                try {
                    writer.write(String.format("%s\n", book.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
