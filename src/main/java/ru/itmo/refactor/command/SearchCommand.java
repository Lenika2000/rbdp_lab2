package ru.itmo.refactor.command;
import ru.itmo.refactor.exceptions.IncorrectDataException;
import ru.itmo.refactor.model.Book;
import ru.itmo.refactor.utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class SearchCommand implements Command {

    private final Reader reader;
    private final Writer writer;
    private final Collection<Book> books;
    private final int BY_TITLE = 1;
    private final int BY_AUTHOR = 2;
    private final int BY_ISBN = 3;
    private final int BY_KEYWORDS = 4;

    public SearchCommand(Reader reader, Writer writer, Collection<Book> books) {
        this.reader = reader;
        this.writer = writer;
        this.books = books;
    }

    @Override
    public void execute() throws IOException, IncorrectDataException {
        BufferedReader br = new BufferedReader(reader);
        Set<Book> selectedBooks = new HashSet<>();
        writer.write("Select search criteria:\n1.By title or its fragment\n" +
                "2.By the name of the author\n3.By ISBN\n4.By keywords\nPlease print number.\n");
        writer.flush();
        int flag = Integer.parseInt(br.readLine());
        switch (flag) {
            case BY_TITLE:
                writer.write("Please print book's title or its fragment\n");
                writer.flush();
                String title = br.readLine();
                Validator.validateLatinLetters(title, "The title of the book can only consist of Latin letters\n");
                selectedBooks = books.stream().filter(b -> b.getTitle().contains(title)).collect(Collectors.toSet());
                break;
            case BY_AUTHOR:
                writer.write("Please print book's author\n");
                writer.flush();
                String author = br.readLine();
                Validator.validateLatinLetters(author, "The author of the book can only consist of Latin letters\n");
                selectedBooks = books.stream().filter(b -> b.getAuthorName().contains(author)).collect(Collectors.toSet());
                break;
            case BY_ISBN:
                writer.write("Please print book's ISBN (10 digits without spaces or other signs)\n");
                writer.flush();
                String isbn = br.readLine();
                Validator.validateDigits(isbn, "The ISBN of the book can only consist of 10 digits without spaces or other signs\n");
                selectedBooks = books.stream().filter(b -> b.getIsbn().contains(isbn)).collect(Collectors.toSet());
                break;
            case BY_KEYWORDS:
                writer.write("Please print keywords, use a space as a separator\n");
                writer.flush();
                String keywords = br.readLine();
                String[] values = keywords.split("\\s+");
                for(String val: values) {
                    selectedBooks.addAll(books.stream()
                            .filter(b -> b.getTitle().contains(val))
                            .peek(b -> b.incrementKeyWordsCounter())
                            .collect(Collectors.toSet()));
                    selectedBooks.addAll(books.stream()
                            .filter(b -> b.getAuthorName().contains(val))
                            .peek(b -> b.incrementKeyWordsCounter())
                            .collect(Collectors.toSet()));
                    selectedBooks.addAll(books.stream().filter(b -> b.getIsbn().contains(val))
                            .peek(b -> b.incrementKeyWordsCounter())
                            .collect(Collectors.toSet()));
                    selectedBooks.addAll(books.stream().filter(b -> b.getAnnotation().contains(val))
                            .peek(b -> {
                                b.incrementKeyWordsCounter();
                                b.setKeyWordsInAnnotation(true);
                            })
                            .collect(Collectors.toSet()));
                }
                break;
            default:
                throw new IncorrectDataException("The entered expression does not satisfy the condition: a number between 1 and 4\n");
        }
        if (books.size() == 0) {
            writer.write("No books were found by this criteria.\n");
        } else {
            selectedBooks.stream().sorted(Comparator.comparing(Book::getKeyWordsCounter).reversed()).forEach(book -> {
                try {
                    book.setKeyWordsCounter(0);
                    writer.write(String.format("%s\n", book.toString()));
                    book.setKeyWordsInAnnotation(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
